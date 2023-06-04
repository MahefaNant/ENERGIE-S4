package inserted;

import java.sql.SQLException;
import java.util.Arrays;

import database.connectivity.PGConnectivity;

public class Batterie extends Energie{
    
    private String idEnergie ;
    private Double puissance;
    private String titre;
    private String type;
    private Double prix;
    private Double seuil;

    public Batterie() {
        super("idEnergie", "v_batterie");
    }

    public Batterie(String idEnergie, Double puissance, String titre, String type,
            Double prix) {
        super("idEnergie", "v_batterie");
        this.idEnergie = idEnergie;
        this.puissance = puissance;
        this.titre = titre;
        this.type = type;
        this.prix = prix;
    }
    public static void main(String[] args) throws SQLException, Exception {
       Batterie[] batts = getAppropriateBatts(Materiel.getList());
       for (Batterie batt : batts) {
           System.out.println(batt.getSeuil());
       }
    }
    
    public static Batterie[] getAppropriateBatts(Materiel[] mats) throws SQLException, Exception{
        Energie[] engs = getAppropriateEngs(mats, getList()) ;
        return Arrays.copyOf(engs, engs.length, Batterie[].class);
    }

    public static Batterie[] getList() throws SQLException, Exception{
        Inserted[] ins = new Batterie().select(PGConnectivity.getCon()); 
        return Arrays.copyOf(ins,ins.length,Batterie[].class);
    }


    @Override
    public String getIdEnergie() {
        return idEnergie;
    }

    @Override
    public void setIdEnergie(String idEnergie) {
        this.idEnergie = idEnergie;
    }

    public Double getPuissanceWithSeuil() {
        return puissance * (100-seuil) / 100;
    }

    public Double getPuissance() {
        return puissance;
    }

    @Override
    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getSeuil() {
        return seuil;
    }

    public void setSeuil(Double seuil) {
        this.seuil = seuil;
    }

    
    

    
}
