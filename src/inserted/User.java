package inserted;

import java.sql.SQLException;
import java.util.Arrays;

import database.connectivity.PGConnectivity;

public class User extends Inserted {
    private String idUser;
    private String nom;
    
    public User() {
        super("idUser", "users");
    }

    public User(String idUser, String nom) {
        super("idUser", "users");
        this.idUser = idUser;
        this.nom = nom;
    }

    public static User[] getList() throws SQLException, Exception{
        Inserted[] ins = new User().select(PGConnectivity.getCon()); 
        return Arrays.copyOf(ins,ins.length,User[].class);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
