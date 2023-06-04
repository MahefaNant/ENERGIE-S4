insert into energie (puissance, titre, type, prix)
values (200, 'SunPower 200', 'Solaire', 20000),
    (300, 'SunPower 300', 'Solaire', 30000),
    (500, 'QCells 500', 'Solaire', 50000),
    (1000, 'QCells 1000', 'Solaire', 100000),
    (2000, 'QCells 2000', 'Solaire', 200000),
    (50, 'Varta 50','Batterie', 15000),
    (100, 'Varta 100', 'Batterie', 20000),
    (200, 'Varta 200', 'Batterie', 25000),
    (500, 'Bosch 500', 'Batterie', 50000),
    (1000, 'Bosch 1000', 'Batterie', 100000);

insert into users (nom) values ('Test');

insert into materiel (idUser,nom,puissance,debutHeure,finHeure) VALUES
('USR001','app 1',250,'12:00','13:00');

insert into seuilProfondeur values 
('ENG017',30),
('ENG018',25),
('ENG019',20),
('ENG020',15),
('ENG021',40);
