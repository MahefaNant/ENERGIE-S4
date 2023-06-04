create or replace view v_batterie AS
select e.*,
    s.valeur seuil
from energie e
    join seuilProfondeur s on s.idEnergie = e.idEnergie;

create or replace view v_solaire AS
select *
from energie
where type = 'Solaire';

create or replace view v_produit as
select e.*
from energie e
    join produit p on p.idEnergie = e.idEnergie;

create or replace function check_user() RETURNS TRIGGER LANGUAGE PLPGSQL AS $$
DECLARE user_id VARCHAR;
BEGIN
    select idUser into user_id
    from users
    where nom = NEW.nom;
    if user_id is null then 
        return new;
    else
        delete from materiel
        where idUser = user_id;
        delete from produit
        where idUser = user_id;
        return null;
    end if;
END;
$$;

create trigger ins_user before
insert on users for each row execute procedure check_user();