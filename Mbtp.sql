CREATE TABLE client(
   id_client SERIAL,
   numero VARCHAR(50) ,
   PRIMARY KEY(id_client),
   UNIQUE(numero)
);
CREATE TABLE typemaison(
   id_typemaison SERIAL,
   nomtypemaison VARCHAR(50) ,
   descriptions TEXT,
   duree_j FLOAT check (duree_j>0),
   surface FLOAT check (surface>0),
   PRIMARY KEY(id_typemaison),
   UNIQUE(nomtypemaison)
);
CREATE TABLE typefinition(
   id_typefinition SERIAL,
   nomfinition VARCHAR(50) ,
   descriptions TEXT,
   PRIMARY KEY(id_typefinition),
   UNIQUE(nomfinition)
);
CREATE TABLE tauxtariffinition(
   id_tauxtariffinition SERIAL,
   tauxaugment FLOAT check(tauxaugment>=0),
   datemodif DATE,
   id_typefinition INT NOT NULL,
   PRIMARY KEY(id_tauxtariffinition),
   FOREIGN KEY(id_typefinition) REFERENCES typefinition(id_typefinition)
);
create table lieu(
   id_lieu serial,
   lieu varchar,
   PRIMARY KEY(id_lieu),
   unique(lieu)
);

CREATE TABLE clienttravaux(
   id_clienttravaux SERIAL,
   nomtraveaux VARCHAR(50) ,
   datedebut DATE,
   datehfin TIMESTAMP,
   datecreation DATE,
   id_client INT NOT NULL,
   id_typemaison INT NOT NULL,
   id_lieu Int,
   id_tauxtariffinition int not null,
   ref_devis varchar unique,
   PRIMARY KEY(id_clienttravaux),
   FOREIGN KEY(id_client) REFERENCES client(id_client),
   FOREIGN KEY(id_typemaison) REFERENCES typemaison(id_typemaison),
   FOREIGN KEY(id_lieu) REFERENCES lieu(id_lieu),
   FOREIGN KEY(id_tauxtariffinition) REFERENCES tauxtariffinition(id_tauxtariffinition)
);
CREATE TABLE detaidevisclient(
   id_detaidevisclient SERIAL,
   quantite FLOAT  check(quantite>0),
   tarifunitaire FLOAT check(tarifunitaire>0),
   id_travaudetail INT NOT NULL,
   id_clienttravaux INT NOT NULL,
   PRIMARY KEY(id_detaidevisclient),
   FOREIGN KEY(id_travaudetail) REFERENCES traveaudetail(id_travaudetail),
   FOREIGN KEY(id_clienttravaux) REFERENCES clienttravaux(id_clienttravaux),
   unique(id_travaudetail,id_clienttravaux)
);

CREATE TABLE payement(
   id_payement SERIAL,
   montant FLOAT , check( montant >= 0),
   datepaye DATE,
   id_clienttravaux INT NOT NULL,
   ref_paiement varchar unique,
   PRIMARY KEY(id_payement),
   FOREIGN KEY(id_clienttravaux) REFERENCES clienttravaux(id_clienttravaux)
);
CREATE TABLE travaux(
   id_travaux SERIAL,
   codetravau VARCHAR(50),
   designation VARCHAR(50) ,
   PRIMARY KEY(id_travaux),
   UNIQUE(codetravau),
   UNIQUE(designation)
);
CREATE TABLE unite(
   id_unite SERIAL,
   unite VARCHAR(50) ,
   PRIMARY KEY(id_unite),
   UNIQUE(unite)
);

CREATE TABLE traveaudetail(
   id_travaudetail SERIAL,
   codetravaud VARCHAR(50),
   rang INT ,unique(codetravaud,rang),
   id_unite INT NOT NULL,
   id_travaux INT NOT NULL,
   designationd VARCHAR(50),
   PRIMARY KEY(id_travaudetail),
   FOREIGN KEY(id_unite) REFERENCES unite(id_unite),
   FOREIGN KEY(id_travaux) REFERENCES travaux(id_travaux)
);
CREATE TABLE devis_travaux(
   id_devis_travaux SERIAL,
   quantite FLOAT  check(quantite>=0),
   id_travaudetail INT NOT NULL,
   id_typemaison INT NOT NULL,
   datequantite date ,
   PRIMARY KEY(id_devis_travaux),
   FOREIGN KEY(id_travaudetail) REFERENCES traveaudetail(id_travaudetail),
   FOREIGN KEY(id_typemaison) REFERENCES typemaison(id_typemaison)
);


CREATE TABLE tariftravaux(
   id_tariftravaux SERIAL,
   tarifunitaire FLOAT  check(tarifunitaire>0),
   id_travaudetail INT NOT NULL,
   datetarif date,
   PRIMARY KEY(id_tariftravaux),
   FOREIGN KEY(id_travaudetail) REFERENCES traveaudetail(id_travaudetail)
);



--drop table tariftravaux,devis_travaux,traveaudetail
CREATE TABLE admin(
   id_admin SERIAL,
   nom VARCHAR(50) ,
   nee DATE,
   pwd VARCHAR(50) ,
   mail VARCHAR(50) ,
   PRIMARY KEY(id_admin),
   UNIQUE(mail)
);


CREATE table maisontravauxcsv(
   id_maisontravauxcsv SERIAL primary key,
   type_maison VARCHAR,
   descriptions VARCHAR(200),
   surface VARCHAR,
   code_travaux VARCHAR,
   type_travaux VARCHAR,
   unite VARCHAR,
   prix_unitaire VARCHAR,
   quantite VARCHAR,
   duree_travaux VARCHAR
);
--1)
create or replace view csv_typemaison as 
   select type_maison,descriptions,surface,duree_travaux from maisontravauxcsv group by type_maison,descriptions,surface,duree_travaux;
--2)
create or replace view csv_unite as 
   select unite from maisontravauxcsv group by unite;
--3)
create or replace view csv_traveaudetail as 
   select mt.code_travaux,mt.type_travaux,mt.unite,u.id_unite
   from maisontravauxcsv as mt
   join unite as u on mt.unite=u.unite
   group by mt.code_travaux,mt.type_travaux,mt.unite,u.id_unite;
--4)
create or replace view csv_tariftravaux as
   select td.id_travaudetail,mt.prix_unitaire 
   from maisontravauxcsv as mt
   join traveaudetail as td on td.codetravaud=mt.code_travaux
   group by td.id_travaudetail,mt.prix_unitaire;
--5)
create or replace view csv_devis_travaux as 
   select tm.id_typemaison,td.id_travaudetail,mt.quantite
   from maisontravauxcsv as mt
   join typemaison as tm on tm.nomtypemaison=mt.type_maison
   join traveaudetail as td on td.codetravaud=mt.code_travaux
   group by tm.id_typemaison,td.id_travaudetail,mt.quantite;

create table deviscsv(
   id_deviscsv serial primary key,
   client VARCHAR,
   ref_devis VARCHAR,
   type_maison VARCHAR,
   finition VARCHAR,
   taux_finition VARCHAR,
   date_devis VARCHAR,
   date_debut VARCHAR,
   lieu VARCHAR
);
--6)
create or replace view csv_lieu as 
   select lieu from deviscsv group by lieu;
--7)
create or replace view csv_typefinition as
   select finition from deviscsv group by finition;
--8)
create or replace view csv_tauxtariffinition as 
   select tf.id_typefinition,dv.taux_finition
   from deviscsv as dv
   join typefinition as tf on tf.nomfinition=dv.finition
   group by tf.id_typefinition,dv.taux_finition;
--9)
create or replace view csv_client as 
   select client from deviscsv group by client;
--10)
create or replace view csv_clienttravaux as
   select c.id_client,
         dv.ref_devis,
         tm.id_typemaison,
         ltrf_v.id_tauxtariffinition,
         dv.date_devis,
         dv.date_debut,
         l.id_lieu
         from deviscsv as dv
         join client as c on c.numero=dv.client
         join typemaison as tm on tm.nomtypemaison=dv.type_maison
         join typefinition as tp on tp.nomfinition=dv.finition
         join lasttauxtariffinition_v as ltrf_v on ltrf_v.id_typefinition=tp.id_typefinition
         join lieu as l on l.lieu=dv.lieu
         group by 
         c.id_client,
         dv.ref_devis,
         tm.id_typemaison,
         ltrf_v.id_tauxtariffinition,
         dv.date_devis,
         dv.date_debut,
         l.id_lieu;
create table payementcsv(
   id_payementcsv serial primary key,
   ref_devis VARCHAR,
   ref_paiement VARCHAR,
   date_paiement VARCHAR,
   montant VARCHAR(100)
);
--11)
create or replace view csv_payement as 
   select ct.id_clienttravaux,pcsv.ref_paiement,pcsv.date_paiement,pcsv.montant
   from payementcsv as pcsv
   join clienttravaux as ct on ct.ref_devis=pcsv.ref_devis
   group by ct.id_clienttravaux,pcsv.ref_paiement,pcsv.date_paiement,pcsv.montant;