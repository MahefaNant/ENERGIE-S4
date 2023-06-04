package inserted;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import database.connectivity.PGConnectivity;

public class Produit extends Inserted{
    private String idUser;
    private String idEnergie;

    public Produit() {
        super("", "produit");
    }

    public Produit(String idUser, String idEnergie) {
        super("", "produit");
        this.idUser = idUser;
        this.idEnergie = idEnergie;
    }

    public static Produit[] getListOf(String id_user) throws SQLException, Exception{
        Inserted[] ins = new Produit(id_user,"").select(PGConnectivity.getCon()); 
        return Arrays.copyOf(ins,ins.length,Produit[].class);
    } 

    public static void insertAll(Vector<Energie> engs,String user_id) throws Exception{
        Energie[] list = Energie.getList();
        for (Energie eng : engs) {
            new Produit(user_id,Energie.getId(eng, list)).insert(PGConnectivity.getCon());
        }
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(String idEnergie) {
        this.idEnergie = idEnergie;
    }

    
    
    
}
