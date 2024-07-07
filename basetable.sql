create database stock;
\c stock;
--fifo / lifo 
create table methodgestion(
    idmethodgestion serial primary key,
    codemethodgestion varchar(30)
);
--(code method)
create table article(
    idarticle serial primary key,
    codearticle varchar(30) unique,
    nomarticle varchar(30),
    unite varchar(20),
    idmethodgestion int references methodgestion(idmethodgestion)
);
create table magasin(
    idmagasin serial primary key,
    nommagasin varchar(30),
    adressmagasin varchar(30)
);
create table movementstockin(
    idmovementstockin serial primary key,
    idarticle int references article(idarticle),
    idmagasin int references magasin(idmagasin),
    quantiteinitial float check (quantiteinitial>=0),
    prixunit float,
    datein date,
    idunitarticle int references unitarticle(idunitarticle)
);
///alter table movementstockin add column idunitarticle int references unitarticle(idunitarticle)

-----le miditra fotsiny no mere an'le mizarazara    /    donc ato @ movementmereout zany no mijery ny etat de validation
create table movementmereout(
    idmovementmereout serial primary key,
    idmagasin int references magasin(idmagasin),
    idarticle int references article(idarticle),
    quantite float,
    dateout date,
    etat int
);
----1: non valide , 11 :valide
create table validationout(
    idvalidationout serial primary key,
    idmovementmereout int references movementmereout(idmovementmereout),
    datevalidation date
);
create table movementstockout(
    idmovementstockout serial primary key,
    idmovementmereout int references movementmereout(idmovementmereout),
    idmovementstockin int references movementstockin(idmovementstockin),
    quantiteout float check (quantiteout>=0)
);
create table unitarticle(
    idunitarticle serial primary key,
    idarticle int references article(idarticle),
    quantiteunitaire float,
    unitname varchar
);


---date de validation no mitondra azy : fifo lifo , date anterieur sy ulterieur
-------DECOMPOSITION : MANAO VIEW @ STOCKIN IZAY TENA MISY NY QUANTITE MARINA AO AMINY


--validation + etat
--etat --> 10 20 30
-- create or replace view v_movementmereoutvalidation as
--     select mo.*,vo.idvalidation,vo.datevalidation from movementmereout as mo left join validationout as vo on mo.idmovementmereout=vo.idmovementmereout;







-- select entree.idarticle,entree.idmagasin,sum(entree.quantite) as quantite ,sum(entree.prixtotal) as prixtotal
-- from (
--         select mi.idarticle,mi.idmagasin,sum(mi.quantiteinitial) as quantite,sum(mi.prixunit*mi.quantiteinitial) as prixtotal from movementstockin as mi where mi.datein< group by mi.idarticle,mi.idmagasin 
--         union 
--         select a.idarticle,m.idmagasin,0 as quantite,0 as prixtotal from article as a cross join magasin as m 
--     ) as entree

-----ze niditra rehetra t@ io date io sy ze navoaka rehetra t@ io date io
