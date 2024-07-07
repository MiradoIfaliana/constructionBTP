-- create or replace function devis_travaux_f (datefin date)
--     returns table (quantite FLOAT,id_travaudetail INT,id_typemaison INT,datequantite date ) As $$
--     BEGIN
--     return query
--         select dt.quantite ,dt.id_travaudetail ,dt.id_typemaison,dt.datequantite from devis_travaux as dt
--         join (select dt2.id_travaudetail,dt2.id_typemaison,dt2.quantite,max(dt2.datequantite) as datequantite from devis_travaux as dt2 
--                   where dt2.datequantite<=datefin
--                   group by dt2.id_travaudetail,dt2.id_typemaison,dt2.quantite
--             )as dt_tab
--         on (dt.id_travaudetail=dt_tab.id_travaudetail and dt_tab.id_typemaison=dt.id_typemaison and dt.datequantite=dt_tab.datequantite ) ;
--     end;
--     $$ language plpgsql;
-- select * from devis_travaux_f ('2024-09-09');



-- create or replace function tariftravaux_f (datefin date)
--     returns table (tarifunitaire float , id_travaudetail int, datetarif date) As $$
--     BEGIN
--     return query
--         select dt.tarifunitaire ,dt.id_travaudetail ,dt.datetarif from tariftravaux as dt
--         join (select dt2.id_travaudetail,max(dt2.datetarif) as datetarif from tariftravaux as dt2 
--                   where dt2.datetarif<=datefin
--                   group by dt2.id_travaudetail
--             )as dt_tab
--         on (dt.id_travaudetail=dt_tab.id_travaudetail and dt_tab.datetarif=dt.datetarif ) ;
--     end;
--     $$ language plpgsql;
-- select * from tariftravaux_f ('2024-09-09');


-- create or replace function tauxtariffinition_f (datefin date)
--     returns table (tauxaugment float,  id_typefinition int,datemodif date ) As $$
--     BEGIN
--     return query
--         select dt.tauxaugment ,dt.id_typefinition ,dt.datemodif from tauxtariffinition as dt
--         join (select dt2.id_typefinition,max(dt2.datemodif) as datemodif from tauxtariffinition as dt2 
--                   where dt2.datemodif<=datefin
--                   group by dt2.id_typefinition
--             )as dt_tab
--         on (dt.id_typefinition=dt_tab.id_typefinition and dt_tab.datemodif=dt.datemodif ) ;
--     end;
--     $$ language plpgsql;
-- select * from tauxtariffinition_f('2024-09-09');



--anjarany ny manao hoe lasa 0 ndray ity

------les travaux details par type maison
create or replace view typemdevis_v as
SELECT 
    tm.id_typemaison,
    td.id_travaux,
    dt.id_travaudetail
    from typemaison as tm
    join devis_travaux as dt on dt.id_typemaison=tm.id_typemaison
    join traveaudetail as td on td.id_travaudetail=dt.id_travaudetail
    group by tm.id_typemaison,
    td.id_travaux,
    dt.id_travaudetail;
------les travaux details par type maison (plus detaillé)
create or replace view travauxpartype_v as 
select 
    tmd_v.id_typemaison,
    tm.nomtypemaison,
    tmd_v.id_travaux,
    tr.codetravau,
    tr.designation,
    tmd_v.id_travaudetail,
    td.codetravaud,
    td.designationd,
    u.unite
    from typemdevis_v as tmd_v
    join typemaison as tm on tmd_v.id_typemaison=tm.id_typemaison
    join travaux as tr on tmd_v.id_travaux=tr.id_travaux
    join traveaudetail as td on tmd_v.id_travaudetail=td.id_travaudetail
    join unite as u on td.id_unite=u.id_unite
    order by tmd_v.id_typemaison,
    tr.codetravau,
    td.codetravaud,
    td.rang;

---detail devis (travaildetail/quantite/tarif) par client
create or replace view devisdetailclient_v as 
select
    ct.id_clienttravaux,
    ct.id_client,
    c.numero,
    tprt_v.id_typemaison,
    tprt_v.nomtypemaison,
    tprt_v.id_travaux,
    tprt_v.codetravau,
    tprt_v.designation,
    tprt_v.id_travaudetail,
    tprt_v.codetravaud,
    tprt_v.designationd,
    tprt_v.unite,
    ddc.quantite,
    ddc.tarifunitaire as pu,
    tf.nomfinition,
    trfn.tauxaugment,
    ct.datedebut ,
    ct.datehfin,
    ct.datecreation
    from clienttravaux as ct
    join client as c on ct.id_client=c.id_client
    join travauxpartype_v as tprt_v 
        on tprt_v.id_typemaison=ct.id_typemaison
    join tauxtariffinition as trfn on trfn.id_tauxtariffinition=ct.id_tauxtariffinition
    join typefinition as tf on tf.id_typefinition=trfn.id_typefinition---
    join detaidevisclient as ddc on (ddc.id_clienttravaux=ct.id_clienttravaux and tprt_v.id_travaudetail=ddc.id_travaudetail )
    order by tprt_v.codetravau,tprt_v.codetravaud;

----devis total existant
create or replace view devistotal_v as 
    select 
    id_clienttravaux,
    sum((tauxaugment*pu*quantite) + (pu*quantite)) as total
    from
    devisdetailclient_v 
    group by id_clienttravaux;
----paye total existant
create or replace view devispayetotal_v as
    select tb.id_clienttravaux,sum(tb.montant) as payetotal from(
    select id_clienttravaux,sum(montant) as montant from payement group by id_clienttravaux
    union
    select id_clienttravaux,0 from clienttravaux
    ) as tb group by tb.id_clienttravaux;

create or replace view detailpaye_v as 
select dvt_v.id_clienttravaux,dvt_v.total as totalapaye,dvp_v.payetotal,(dvt_v.total-dvp_v.payetotal) as restepaye
    from devistotal_v as dvt_v
    join devispayetotal_v as dvp_v
        on dvt_v.id_clienttravaux=dvp_v.id_clienttravaux;

create or replace view listedevisclient_v as
    select 
    dvdtc_v.id_clienttravaux,
    dvdtc_v.id_client,
    dvdtc_v.numero,
    dvdtc_v.id_typemaison,
    dvdtc_v.nomtypemaison,
    dvdtc_v.nomfinition,
    dvdtc_v.datedebut,
    dvdtc_v.datehfin,
    dvdtc_v.datecreation,
    dp_v.totalapaye,
    dp_v.payetotal,
    dp_v.restepaye,
    ct.ref_devis,
    ct.id_lieu,
    l.lieu
    from devisdetailclient_v  as dvdtc_v
    join detailpaye_v as dp_v on dp_v.id_clienttravaux=dvdtc_v.id_clienttravaux
    join clienttravaux as ct on dvdtc_v.id_clienttravaux=ct.id_clienttravaux
    join lieu as l on l.id_lieu=ct.id_lieu
    group by 
    dvdtc_v.id_clienttravaux,
    dvdtc_v.id_client,
    dvdtc_v.numero,
    dvdtc_v.id_typemaison,
    dvdtc_v.nomtypemaison,
    dvdtc_v.nomfinition,
    dvdtc_v.datedebut,
    dvdtc_v.datehfin,
    dvdtc_v.datecreation,
    dp_v.totalapaye,
    dp_v.payetotal,
    dp_v.restepaye,
    ct.ref_devis,
    ct.id_lieu,
    l.lieu;
create or replace view payementclient_v as
select  
        dvdt_v.id_clienttravaux,
        dvdt_v.id_client,
        dvdt_v.numero,
        dvdt_v.id_typemaison,
        dvdt_v.nomtypemaison,
        dvdt_v.nomfinition,
        dvdt_v.datedebut,
        dvdt_v.datehfin,
        dvdt_v.datecreation,
        dp_v.totalapaye,
        dp_v.payetotal,
        dp_v.restepaye
        from devisdetailclient_v as dvdt_v
        join detailpaye_v as dp_v on dvdt_v.id_clienttravaux=dp_v.id_clienttravaux
        group by         
        dvdt_v.id_clienttravaux,
        dvdt_v.id_client,
        dvdt_v.numero,
        dvdt_v.id_typemaison,
        dvdt_v.nomtypemaison,
        dvdt_v.nomfinition,
        dvdt_v.datedebut,
        dvdt_v.datehfin,
        dvdt_v.datecreation,
        dp_v.totalapaye,
        dp_v.payetotal,
        dp_v.restepaye;

select 
    EXTRACT('YEAR' from datecreation) as annee, 
    EXTRACT('MONTH' from datecreation) as mois,
    from totaldevispardate_v

    
create or replace view totaldevisparanneeparmois_v as
select  tab.annee,tab.mois,sum(tab.total) as total from(
    select
        EXTRACT('YEAR' from ct.datecreation) as annee,
        EXTRACT('MONTH' from ct.datecreation) as mois,
        EXTRACT('DAY' from ct.datecreation) as jour,
        dvttl_v.total
        from clienttravaux as ct
        join devistotal_v as dvttl_v
            on ct.id_clienttravaux=dvttl_v.id_clienttravaux
    ) as tab group by tab.annee,tab.mois;


create or replace view devisparannee_v as 
    select annee,sum(total) as montanttotal from totaldevisparanneeparmois_v group by annee;

create or replace view devistotalall_v as
    select sum(montanttotal) as montanttotal from devisparannee_v;

create or replace view payetotalall_v as
    select sum(montant) as payetotal from payement;

create or replace view devis_paye_totalall_v as
select d_v.montanttotal as devistotal,p_v.payetotal from devistotalall_v as d_v,payetotalall_v as p_v;
---2024 zao no total azo
create or replace view devisparmoisparannee_v as 
    select tab.mois::int,tab.annee::int,sum(tab.montanttotal) as montanttotal  from(
        select 1 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 2 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 3 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 4 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 5 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 6 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 7 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 8 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 9 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 10 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 11 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 12 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union
        select 12 as mois, annee,0::float as montanttotal from totaldevisparanneeparmois_v group by annee
        union 
        select mois, annee,total::float as montanttotal from totaldevisparanneeparmois_v 
    ) as tab  group by tab.mois,tab.annee;

create or replace view payemoisannee_v as
select 
    EXTRACT('YEAR' from datepaye)::int as annee, 
    EXTRACT('MONTH' from datepaye)::int as mois,
    sum(montant) as montant
    from 
    payement
    group by  EXTRACT('YEAR' from datepaye), EXTRACT('MONTH' from datepaye);
create or replace view payeannee_v as
    select sum(montant) as montant,annee from payemoisannee_v group by annee;


create or replace view tauxtariffinition_v as
   select dt.id_tauxtariffinition,dt.tauxaugment ,dt.id_typefinition ,dt.datemodif from tauxtariffinition as dt
        join (select dt2.id_typefinition,max(dt2.datemodif) as datemodif from tauxtariffinition as dt2 
                  group by dt2.id_typefinition
            )as dt_tab
        on (dt.id_typefinition=dt_tab.id_typefinition and dt_tab.datemodif=dt.datemodif ) ;
-- create or replace view lasttauxtariffinition_v as
--     select dt.id_tauxtariffinition,dt.tauxaugment ,dt.id_typefinition ,dt.datemodif from tauxtariffinition as dt
--     join( select max(id_tauxtariffinition) as id_tauxtariffinition,id_typefinition ,datemodif from tauxtariffinition_v group by id_typefinition ,datemodif ) as dt1
--     on (dt.id_tauxtariffinition=dt1.id_tauxtariffinition and dt.id_typefinition=dt1.id_typefinition and dt.datemodif=dt1.datemodif);

create or replace view typefinition_v as
select tf.id_typefinition,tf.nomfinition,tf.descriptions,txf_v.tauxaugment,txf_v.datemodif 
   from typefinition as tf join lasttauxtariffinition_v as txf_v on tf.id_typefinition=txf_v.id_typefinition;

create or replace view devis_travaux_v as
   select dt.id_devis_travaux,dt.quantite ,dt.id_travaudetail ,dt.id_typemaison,dt.datequantite from devis_travaux as dt
        join (select dt2.id_travaudetail,dt2.id_typemaison,max(dt2.datequantite) as datequantite from devis_travaux as dt2 
                  group by dt2.id_travaudetail,dt2.id_typemaison
            )as dt_tab
        on (dt.id_travaudetail=dt_tab.id_travaudetail and dt.id_typemaison=dt_tab.id_typemaison and dt_tab.datequantite=dt.datequantite ) ;

-- create or replace view lastdevis_travaux_v as
--     select dt.id_devis_travaux,dt.quantite ,dt.id_travaudetail ,dt.id_typemaison,dt.datequantite
--     from devis_travaux as dt
--     join (
--         select max(id_devis_travaux) as id_devis_travaux,id_travaudetail ,id_typemaison ,datequantite from devis_travaux_v group by id_travaudetail ,id_typemaison ,datequantite
--     )as dt1 on (dt1.id_devis_travaux=dt.id_devis_travaux and dt.id_travaudetail=dt1.id_travaudetail and dt.id_typemaison=dt.id_typemaison and dt1.datequantite=dt.datequantite ) ;


create or replace view tariftravaux_v as
   select dt.id_tariftravaux,dt.tarifunitaire ,dt.id_travaudetail ,dt.datetarif from tariftravaux as dt
        join (select dt2.id_travaudetail,max(dt2.datetarif) as datetarif from tariftravaux as dt2 
                  group by dt2.id_travaudetail
            )as dt_tab
        on (dt.id_travaudetail=dt_tab.id_travaudetail and dt_tab.datetarif=dt.datetarif ) ;

-- create or replace view lasttariftravaux_v as
--     select dt.id_tariftravaux,dt.tarifunitaire ,dt.id_travaudetail ,dt.datetarif 
--     from tariftravaux as dt
--     join (
--         select max(id_tariftravaux) as id_tariftravaux,id_travaudetail,datetarif from tariftravaux_v group by id_travaudetail,datetarif
--     )as dt1 on dt.id_tariftravaux=dt1.id_tariftravaux and dt.datetarif=dt1.datetarif and dt.id_travaudetail=dt1.id_travaudetail;


-- Alter table tariftravaux add constraint tariftravaux_c unique (datetarif,id_travaudetail);
-- Alter table tauxtariffinition add constraint tauxtariffinition_c unique (datemodif,id_typefinition);
-- Alter table devis_travaux add constraint devis_travaux_c unique (id_travaudetail,id_typemaison,datequantite);

-- Alter table tariftravaux drop constraint tariftravaux_c ;
-- Alter table tauxtariffinition drop constraint tauxtariffinition_c ;
-- Alter table devis_travaux drop constraint devis_travaux_c ;

Alter table traveaudetail add constraint uniquecodetd_c unique (codetravaud);


create or replace view lastdevis_travaux_v as
WITH RankedDevis AS (
    SELECT
        id_devis_travaux,
        quantite,
        id_travaudetail,
        id_typemaison,
        datequantite,
        ROW_NUMBER() OVER (
            PARTITION BY id_travaudetail, id_typemaison
            ORDER BY datequantite DESC, id_devis_travaux DESC
        ) AS rank
    FROM
        devis_travaux
)
SELECT
    id_devis_travaux,
    quantite,
    id_travaudetail,
    id_typemaison,
    datequantite
FROM
    RankedDevis
WHERE
    rank = 1;

create or replace view lasttauxtariffinition_v as
WITH RankedTaux AS (
    SELECT
        id_tauxtariffinition,
        tauxaugment,
        datemodif,
        id_typefinition,
        ROW_NUMBER() OVER (
            PARTITION BY id_typefinition
            ORDER BY datemodif DESC, id_tauxtariffinition DESC
        ) AS rank
    FROM
        tauxtariffinition
)
SELECT
    id_tauxtariffinition,
    tauxaugment,
    id_typefinition,
    datemodif
FROM
    RankedTaux
WHERE
    rank = 1;

create or replace view lasttariftravaux_v as
WITH RankedTarif AS (
    SELECT
        id_tariftravaux,
        tarifunitaire,
        datetarif,
        id_travaudetail,
        ROW_NUMBER() OVER (
            PARTITION BY id_travaudetail
            ORDER BY datetarif DESC, id_tariftravaux DESC
        ) AS rank
    FROM
        tariftravaux
)
SELECT
    id_tariftravaux,
    tarifunitaire,
    id_travaudetail,
    datetarif
    
FROM
    RankedTarif
WHERE
    rank = 1;

create or replace view quantitetariftravaux_v as 
select 
    tp.id_typemaison,
    lastdv.id_travaudetail,
    lastdv.quantite,
    lasttarif.tarifunitaire
    from typemaison as tp
    join lastdevis_travaux_v as lastdv on tp.id_typemaison=lastdv.id_typemaison
    join lasttariftravaux_v as lasttarif on lasttarif.id_travaudetail=lastdv.id_travaudetail ;

create or replace view traveaudetailtarif_v as
select
   td.id_travaudetail,
   td.codetravaud,
   td.rang,
   td.designationd,
   t_v.id_tariftravaux,
   t_v.tarifunitaire
   from traveaudetail as td 
   join lasttariftravaux_v as t_v on t_v.id_travaudetail=td.id_travaudetail;
