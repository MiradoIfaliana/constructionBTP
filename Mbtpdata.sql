INSERT INTO typemaison (nomtypemaison, descriptions, duree_j)
VALUES ('Maison traditionnelle', 'Une maison construite selon des méthodes traditionnelles', 180),
      ('Maison moderne', 'Une maison contemporaine avec des matériaux modernes', 120),
      ('Maison écologique', 'Une maison respectueuse de environnement', 240),
      ('Maison de campagne', 'Une maison située en pleine nature', 300),
      ('Maison préfabriquée', 'Une maison construite en usine et assemblée sur site', 90);

INSERT INTO typefinition (nomfinition, descriptions)
VALUES 
    ('standard', 'Finition standard avec des matériaux de qualité'),
    ('Gold', 'Finition de luxe avec des matériaux haut de gamme'),
    ('premium', 'Finition premium offrant des fonctionnalités supplémentaires'),
    ('vip', 'Finition VIP avec des équipements exclusifs et des services personnalisés');

INSERT INTO tauxtariffinition (tauxaugment, datemodif, id_typefinition)
VALUES 
    (0.0, '2023-01-01', 1), -- Standard (taux 0%)
    (0.05, '2023-01-01', 2), -- Gold (taux 5%)
    (0.10, '2023-01-01', 3), -- Premium (taux 10%)
    (0.15, '2023-01-01', 4); -- VIP (taux 15%)

INSERT INTO travaux (codetravau, designation)
VALUES 
    ('000', 'Travaux de préparation'),
    ('100', 'Travaux de terrassement'),
    ('200', 'Travaux en infrastructure');

INSERT INTO unite (unite)
VALUES 
    ('m2'),
    ('m3'),
    ('ftt');
INSERT INTO traveaudetail (codetravaud, rang, id_unite, id_travaux, designationd)
VALUES 
    ('001', 1, 2, 1, 'mur de soutènement et demi Clôture ht 1m'),
    ('101', 1, 1, 2, 'Décapage des terrains meubles'),
    ('102', 1, 1, 2, 'Dressage du plateforme'),
    ('103', 1, 2, 2, 'Fouille d ouvrage terrain ferme'),
    ('104', 1, 2, 2, 'Remblai d ouvrage'),
    ('105', 1, 3, 2, 'Travaux d implantation'),
    ('201', 1, 2, 3, 'maçonnerie de moellons, ep= 35cm'),
    ('202', 1, 2, 3, 'béton armé dosée à 350kg/m3, semelles isolées'),
    ('202', 2, 2, 3, 'béton armé dosée à 350kg/m3, amorces poteaux'),
    ('202', 3, 2, 3, 'béton armé dosée à 350kg/m3, chaînage bas de 20x20'),
    ('203', 1, 2, 3, 'Remblai technique'),
    ('204', 1, 2, 3, 'Herrissonage ep=10'),
    ('205', 1, 2, 3, 'Béton ordinaire dosée à 300kg/m3'),
    ('206', 1, 2, 3, 'Chape de 2cm');
INSERT INTO devis_travaux (quantite, id_travaudetail, id_typemaison)
SELECT 
    (RANDOM() + 11) AS quantite,
    traveaudetail.id_travaudetail,
    typemaison.id_typemaison
FROM 
    traveaudetail
CROSS JOIN 
    typemaison;

-- devis_travaux;;
--  id_devis_travaux | quantite | id_travaudetail | id_typemaison | datequantite
--  tariftravaux;
--  id_tariftravaux | tarifunitaire | id_travaudetail | datetarif
insert into detaidevisclient(id_clienttravaux,id_travaudetail,quantite,tarifunitaire)
select t0.id_clienttravaux,t1.id_travaudetail,t1.quantite,t2.tarifunitaire
    from clienttravaux as t0
    join devis_travaux_v as t1 on t0.id_typemaison=t1.id_typemaison
    join tariftravaux_v as t2 on t2.id_travaudetail=t1.id_travaudetail;



INSERT INTO tariftravaux (tarifunitaire, id_travaudetail)
VALUES 
    (150000.0, 1),   -- Tarif pour le travail 001
    (170000.0, 2),   -- Tarif pour le travail 101
    (180000.0, 3),   -- Tarif pour le travail 102
    (190000.0, 4),   -- Tarif pour le travail 103
    (160000.0, 5),   -- Tarif pour le travail 104
    (175000.0, 6),   -- Tarif pour le travail 105
    (110000.0, 7),  -- Tarif pour le travail 201
    (112000.0, 8),  -- Tarif pour le travail 202 Rang 1
    (113000.0, 9),  -- Tarif pour le travail 202 Rang 2
    (114000.0, 10), -- Tarif pour le travail 202 Rang 3
    (111000.0, 11), -- Tarif pour le travail 203
    (165000.0, 12),  -- Tarif pour le travail 204
    (185000.0, 13),  -- Tarif pour le travail 205
    (195000.0, 14);  -- Tarif pour le travail 206

INSERT INTO admin (nom, nee, pwd)
VALUES 
    ('Admin1', '1990-05-15', '1234'),
    ('Admin2', '1985-12-10', '1234'),
    ('Admin3', '1993-07-25', '1234');
insert into lieu (lieu) values('Itosy'),('Anosy');


