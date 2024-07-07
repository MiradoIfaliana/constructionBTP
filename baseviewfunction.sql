create or replace view v_movementstockoutvalide as
    select mo.*,mmo.idmagasin,mmo.idarticle,mmo.dateout,mmo.etat,v.datevalidation from movementstockout as mo
    join movementmereout as mmo on mo.idmovementmereout=mmo.idmovementmereout
    left join validationout as v on mo.idmovementmereout=v.idmovementmereout

-----------seul les valides (is not null):
create or replace function f_totaloutbefore (datefin date)
    returns table (idmovementstockin int,quantiteout float) As $$
    BEGIN
    return query
        select sortie.idmovementstockin,sum(sortie.quantiteout) as quantiteout from(
            select v_mo.idmovementstockin,sum(v_mo.quantiteout) as quantiteout from v_movementstockoutvalide as v_mo 
                where (v_mo.dateout<=datefin and v_mo.datevalidation is not null) group by v_mo.idmovementstockin
                        union
            select mi.idmovementstockin,0 as quantiteout from movementstockin as mi
        ) as sortie group by sortie.idmovementstockin;
    end;
    $$ language plpgsql


create or replace function f_etatbrutebefore (datefin date)
    returns table (idarticle int,idmagasin int,quantitetotalin float,quantitetotalout float,prixtotal float) As $$
    BEGIN
    return query
        select etat.idarticle,etat.idmagasin,sum(etat.quantitetotalin) as quantitetotalin,sum(etat.quantitetotalout) as quantitetotalout,sum(etat.prixtotal) as prixtotal  from(
            select mi.idarticle,mi.idmagasin,sum(mi.quantiteinitial) as quantitetotalin ,sum(f_mo.quantiteout) as quantitetotalout,sum(mi.prixunit*(mi.quantiteinitial-f_mo.quantiteout) ) as prixtotal 
                from movementstockin as mi
                join f_totaloutbefore(datefin) as f_mo on mi.idmovementstockin=f_mo.idmovementstockin 
                where mi.datein<=datefin group by mi.idarticle,mi.idmagasin
            union
            select a.idarticle,m.idmagasin,0 as quantitetotalin,0 as quantitetotalout,0 as prixtotal from article as a cross join magasin as m 
        ) as etat group by etat.idarticle,etat.idmagasin;
    end;
    $$ language plpgsql

-- create or replace function f_etatstockbefore(datefin date)
--     returns table (idarticle int,codearticle varchar,nomarticle varchar, codemethodgestion varchar,idmagasin int,nommagasin varchar,adressmagasin varchar,quantitetotalin float,quantitetotalout float , unite varchar,prixtotal float) As $$
--     BEGIN
--     return query
--     select 
--         f_eb.idarticle,
--         a.codearticle,
--         a.nomarticle,
--         mt.codemethodgestion,
--         f_eb.idmagasin,
--         m.nommagasin,
--         m.adressmagasin,
--         f_eb.quantitetotalin,
--         f_eb.quantitetotalout,
--         a.unite,
--         f_eb.prixtotal
--     from f_etatbrutebefore(datefin) as f_eb
--     join article as a on a.idarticle=f_eb.idarticle
--     join magasin as m on m.idmagasin=f_eb.idmagasin
--     join methodgestion as mt on mt.idmethodgestion=a.idmethodgestion;
--     end;
--     $$ language plpgsql

---- idunitarticle | idarticle | quantiteunitaire | unitname
create or replace view v_articleminunit as
    select ua2.* from 
    (select ua.idarticle,min(quantiteunitaire) as unitairemin from unitarticle as ua group by ua.idarticle ) as ua1
    join unitarticle as ua2 on (ua1.idarticle=ua2.idarticle and ua1.unitairemin=ua2.quantiteunitaire)


create or replace function f_etatstockbefore(datefin date)
    returns table (idarticle int,codearticle varchar,nomarticle varchar, codemethodgestion varchar,idmagasin int,nommagasin varchar,adressmagasin varchar,quantitetotalin float,quantitetotalout float , unite varchar,prixtotal float) As $$
    BEGIN
    return query
    select 
        f_eb.idarticle,
        a.codearticle,
        a.nomarticle,
        mt.codemethodgestion,
        f_eb.idmagasin,
        m.nommagasin,
        m.adressmagasin,
        f_eb.quantitetotalin,
        f_eb.quantitetotalout,
        v_au.unitname as unite,
        f_eb.prixtotal
    from f_etatbrutebefore(datefin) as f_eb
    join article as a on a.idarticle=f_eb.idarticle
    join magasin as m on m.idmagasin=f_eb.idmagasin
    join methodgestion as mt on mt.idmethodgestion=a.idmethodgestion
    left join v_articleminunit as v_au on a.idarticle=v_au.idarticle;
    end;
    $$ language plpgsql



create or replace function f_etatstock2date(datedebut date,datefin date)
    returns table (idarticle int,codearticle varchar,nomarticle varchar, codemethodgestion varchar,idmagasin int,nommagasin varchar,adressmagasin varchar,quantitetotalin float,quantitetotalout float , unite varchar,prixtotal float,quantiteindebut float,quantiteoutdebut float,prixtotaldebut float,datei date,datef date) As $$
    BEGIN
    return query
        select 
        f_ebf.idarticle,
        f_ebf.codearticle,
        f_ebf.nomarticle,
        f_ebf.codemethodgestion,
        f_ebf.idmagasin,
        f_ebf.nommagasin,
        f_ebf.adressmagasin,
        f_ebf.quantitetotalin,
        f_ebf.quantitetotalout,
        f_ebf.unite,
        f_ebf.prixtotal,
        f_ebi.quantitetotalin as quantiteindebut,
        f_ebi.quantitetotalout as quantiteoutdebut,
        f_ebi.prixtotal as prixtotaldebut ,
        datedebut as datei,
        datefin as datef
        from f_etatstockbefore(datefin) as f_ebf
        join f_etatstockbefore(datedebut) as f_ebi
        on (f_ebf.idarticle=f_ebi.idarticle and f_ebf.idmagasin=f_ebi.idmagasin);

    end;
    $$ language plpgsql





create or replace view v_lastmovemereout as 
    select mmo.* from
        (select idmagasin,idarticle,max(dateout) as lastdateout from movementmereout group by idmagasin,idarticle ) as mmol
        join
        (select mvmo.*,vo.datevalidation from movementmereout as mvmo left join validationout as vo on mvmo.idmovementmereout=vo.idmovementmereout ) as mmo
        on (mmol.idmagasin=mmo.idmagasin and mmol.idarticle=mmo.idarticle and mmol.lastdateout=mmo.dateout);

create or replace function f_movein_moveoutbefore (datefin date)
    returns table (idmovementstockin int,idarticle int,idmagasin int,quantiteinitial float,prixunit float, datein date,quantiteout float) As $$
    BEGIN
    return query
        select mi.idmovementstockin,mi.idarticle,mi.idmagasin,mi.quantiteinitial,mi.prixunit,mi.datein,f_tto.quantiteout from movementstockin as mi 
            join f_totaloutbefore(datefin) as f_tto on f_tto.idmovementstockin=mi.idmovementstockin
            where mi.datein<=datefin;
    end;
    $$ language plpgsql

-- create or replace view v_article as
--     select a.*,mt.codemethodgestion from article as a join methodgestion as mt on mt.idmethodgestion=a.idmethodgestion
-----raha date mitovy de regroupena by idv_