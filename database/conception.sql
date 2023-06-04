-- create role energie login password 'energie';
-- create database energie;
-- alter database energie owner to energie;
create table energie (
    idEnergie VARCHAR(6) primary key,
    puissance real not null,
    titre VARCHAR(255),
    type varchar(50),
    prix real
);
create sequence seq_energie;
alter table energie
alter column idEnergie
set default 'ENG' || LPAD(nextval('seq_energie')::text, 6-3, '0');

create table seuilProfondeur( 
    idEnergie VARCHAR(6) NOT NULL,
    valeur real NOT NULL
);

create table users( 
    idUser VARCHAR(6) primary key,
    nom VARCHAR(100) NOT NULL
);
create sequence seq_user;
alter table users
alter column idUser
set default 'USR'||LPAD(nextval('seq_user')::text, 6-3, '0');

create table materiel(
    idMateriel VARCHAR(6) primary key,
    idUser VARCHAR(6) NOT NULL,
    nom VARCHAR(100) not null,
    puissance real not null,
    debutHeure time not null,
    finHeure time not null
);
create sequence seq_materiel;
alter table materiel
alter column idMateriel
set default 'MAT'||LPAD(nextval('seq_materiel')::text, 6-3, '0'),
add foreign key (idUser) references users(idUser);

create table produit (
    idUser VARCHAR(6) not null,
    idEnergie VARCHAR(6) not null
);
alter table produit 
add foreign key (idUser) references users(idUser),
add foreign key (idEnergie) references energie(idEnergie);