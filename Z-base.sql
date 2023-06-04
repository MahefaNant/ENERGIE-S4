create user energie identified by mahefa;
grant dba to energie;

create sequence seq_register;
create sequence seq_registerA;
create sequence seq_registerS;
create sequence seq_registerB;

connect energie;

create table Solaire(
    id_solaire varchar(2),
    nom_solaire varchar(15) default 'solaire',
    puissance integer check (puissance>0),
    prix float check(prix>0)
);

create table Batterie(
    id_batterie varchar(2),
    nom_batterie varchar(15) default 'batterie',
    puissance integer check (puissance>0),
    prix float check(prix>0)
);

create table register(
    id_register varchar(5),
    nom_user varchar(15),
    solaire_puissance integer,
    solaire_prix float,
    batterie_puissance integer,
    batterie_prix float,
    appareil_nom varchar(15),
    appareil_puisance integer,
    appareil_I integer,
    appareil_F integer
);

create table registreA(
    id_register varchar(5),
    nom_user varchar(15),
    appareil_nom varchar(15),
    appareil_puisance integer,
    appareil_I integer,
    appareil_F integer
);

create table registerS(
    id_register varchar(5),
    nom_user varchar(15),
    solaire_puissance integer,
    solaire_prix float
);

create table registerB(
    id_register varchar(5),
    nom_user varchar(15),
    batterie_puissance integer,
    batterie_prix float
);

insert into Solaire(id_solaire,puissance,prix) values ('1',3500,18360);
insert into Solaire(id_solaire,puissance,prix) values ('2',2500,12000);
insert into Solaire(id_solaire,puissance,prix) values ('3',1000,5245);
insert into Solaire(id_solaire,puissance,prix) values ('4',500,2622);

insert into Batterie(id_batterie,puissance,prix) values ('1',3600,18360);
insert into Batterie(id_batterie,puissance,prix) values ('2',2400,12000);
insert into Batterie(id_batterie,puissance,prix) values ('3',1200,5245);
insert into Batterie(id_batterie,puissance,prix) values ('4',600,2622);

/*4 564,00