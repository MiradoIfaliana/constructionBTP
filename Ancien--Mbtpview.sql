create or replace function devis_travaux_f (datefin date)
    returns table (quantite FLOAT,id_travaudetail INT,id_typemaison INT,datequantite date ) As $$
    BEGIN
    return query
        select dt.quantite ,dt.id_travaudetail ,dt.id_typemaison,dt.datequantite from devis_travaux as dt
        join (select dt2.id_travaudetail,dt2.id_typemaison,dt2.quantite,max(dt2.datequantite) as datequantite from devis_travaux as dt2 
                  where dt2.datequantite<=datefin
                  group by dt2.id_travaudetail,dt2.id_typemaison,dt2.quantite
            )as dt_tab
        on (dt.id_travaudetail=dt_tab.id_travaudetail and dt_tab.id_typemaison=dt.id_typemaison and dt.datequantite=dt_tab.datequantite ) ;
    end;
    $$ language plpgsql;
select * from devis_travaux_f ('2024-09-09');

create or replace function tariftravaux_f (datefin date)
    returns table (tarifunitaire float , id_travaudetail int, datetarif date) As $$
    BEGIN
    return query
        select dt.tarifunitaire ,dt.id_travaudetail ,dt.datetarif from tariftravaux as dt
        join (select dt2.id_travaudetail,max(dt2.datetarif) as datetarif from tariftravaux as dt2 
                  where dt2.datetarif<=datefin
                  group by dt2.id_travaudetail
            )as dt_tab
        on (dt.id_travaudetail=dt_tab.id_travaudetail and dt_tab.datetarif=dt.datetarif ) ;
    end;
    $$ language plpgsql;
select * from tariftravaux_f ('2024-09-09');


create or replace function tauxtariffinition_f (datefin date)
    returns table (tauxaugment float,  id_typefinition int,datemodif date ) As $$
    BEGIN
    return query
        select dt.tauxaugment ,dt.id_typefinition ,dt.datemodif from tauxtariffinition as dt
        join (select dt2.id_typefinition,max(dt2.datemodif) as datemodif from tauxtariffinition as dt2 
                  where dt2.datemodif<=datefin
                  group by dt2.id_typefinition
            )as dt_tab
        on (dt.id_typefinition=dt_tab.id_typefinition and dt_tab.datemodif=dt.datemodif ) ;
    end;
    $$ language plpgsql;
select * from tauxtariffinition_f('2024-09-09');



--anjarany ny manao hoe lasa 0 ndray ity
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
tariftravaux_v/devis_travaux_v/tauxtariffinition_v
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
    dvt_f.quantite,
    trft_f.tarifunitaire as pu,
    tf.nomfinition,
    taux_f.tauxaugment,
    ct.datedebut ,
    ct.datehfin,
    ct.datecreation
    from clienttravaux as ct
    join client as c on ct.id_client=c.id_client
    join travauxpartype_v as tprt_v 
        on tprt_v.id_typemaison=ct.id_typemaison
    join typefinition as tf on tf.id_typefinition=ct.id_typefinition
    left join devis_travaux_f (ct.datecreation) as dvt_f on (dvt_f.id_typemaison=ct.id_typemaison and dvt_f.id_travaudetail=tprt_v.id_travaudetail)
    left join tariftravaux_f (ct.datecreation) as trft_f on trft_f.id_travaudetail=tprt_v.id_travaudetail
    left join tauxtariffinition_f(ct.datecreation) as taux_f on taux_f.id_typefinition=ct.id_typefinition
    order by tprt_v.codetravau,tprt_v.codetravaud;

create or replace view devistotal_v as 
    select 
    id_clienttravaux,
    sum((tauxaugment*pu*quantite) + (pu*quantite)) as total
    from
    devisdetailclient_v 
    group by id_clienttravaux;
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
    dp_v.restepaye
    from devisdetailclient_v  as dvdtc_v
    join detailpaye_v as dp_v on dp_v.id_clienttravaux=dvdtc_v.id_clienttravaux
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
    dp_v.restepaye;
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

create or replace view typefinition_v as
select tf.id_typefinition,tf.nomfinition,tf.descriptions,txf_v.tauxaugment,txf_v.datemodif 
   from typefinition as tf join tauxtariffinition_v as txf_v on tf.id_typefinition=txf_v.id_typefinition;

create or replace view devis_travaux_v as
   select dt.id_devis_travaux,dt.quantite ,dt.id_travaudetail ,dt.id_typemaison,dt.datequantite from devis_travaux as dt
        join (select dt2.id_travaudetail,dt2.id_typemaison,max(dt2.datequantite) as datequantite from devis_travaux as dt2 
                  group by dt2.id_travaudetail,dt2.id_typemaison
            )as dt_tab
        on (dt.id_travaudetail=dt_tab.id_travaudetail and dt.id_typemaison=dt_tab.id_typemaison and dt_tab.datequantite=dt.datequantite ) ;

create or replace view tariftravaux_v as
   select dt.id_tariftravaux,dt.tarifunitaire ,dt.id_travaudetail ,dt.datetarif from tariftravaux as dt
        join (select dt2.id_travaudetail,max(dt2.datetarif) as datetarif from tariftravaux as dt2 
                  group by dt2.id_travaudetail
            )as dt_tab
        on (dt.id_travaudetail=dt_tab.id_travaudetail and dt_tab.datetarif=dt.datetarif ) ;

Alter table tariftravaux add constraint tariftravaux_c unique (datetarif,id_travaudetail);
Alter table tauxtariffinition add constraint tauxtariffinition_c unique (datemodif,id_typefinition);
Alter table devis_travaux add constraint devis_travaux_c unique (id_travaudetail,id_typemaison,datequantite);