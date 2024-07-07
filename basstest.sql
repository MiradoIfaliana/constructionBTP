CREATE TABLE test1(
   id_test1 SERIAL PRIMARY key,
   nom1 VARCHAR,
   nee date,
   cree timestamp,
   temp time,
   longue float,
   large double precision,
   age int

);
create table test2(
   id_test2 SERIAL,
   nom2 VARCHAR,
   id_test1 int REFERENCES test1(id_test1)
);
create table csvstock(
   idcsvstock SERIAL PRIMARY key,
   id VARCHAR(50),
   daty VARCHAR(20),
   datyheure VARCHAR(20),
   nom VARCHAR(20),
   nb VARCHAR(50),
   temp VARCHAR(50)
);
insert into test1 (nom1,nee,cree,temp,longue,large,age) values
   ('koko0','2005-02-01','2024-01-11 12:30:00','11:00:09',12.09,13.09,18),
   ('koko1','2005-02-02','2024-01-12 12:31:00','12:00:09',13.09,14.09,19),
   ('koko2','2005-02-03','2024-01-13 12:32:00','13:00:09',14.09,15.09,20),
   ('koko3','2005-02-04','2024-01-14 12:33:00','14:00:09',15.09,16.09,21),
   ('koko4','2005-02-05','2024-01-15 12:34:00','15:00:09',16.09,17.09,22);
insert into test2 (nom2,id_test1) values
   ('jojo1',1),
   ('jojo2',2),
   ('jojo3',3),
   ('jojo4',4),
   ('jojo5',5);
drop table test1,test2;





