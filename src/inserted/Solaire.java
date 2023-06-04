package inserted;

import java.sql.SQLException;
import java.util.Arrays;

import database.connectivity.PGConnectivity;

public class Solaire extends Energie{
    
    private String idEnergie ;
    private Double puissance;
    private String titre;
    private String type;
    private Double prix;

    public Solaire() {
        super("idEnergie", "v_solaire");
    }

    public Solaire(String idEnergie, Double puissance, String titre, String type,
            Double prix) {
        super("idEnergie", "v_solaire");
        this.idEnergie = idEnergie;
        this.puissance = puissance;
        this.titre = titre;
        this.type = type;
        this.prix = prix;
    }
    public static void main(String[] args) throws SQLException, Exception {
       Solaire[] sols = getAppropriateSols(Materiel.getList());
       for (Solaire solaire : sols) {
           System.out.println(solaire.getTitre());
       }
    }

    public static Solaire[] getAppropriateSols (Materiel[] mats) throws SQLException, Exception{
        Energie[] engs = getAppropriateEngs(mats, getList()) ;
        return Arrays.copyOf(engs,engs.length,Solaire[].class);
    }

    public static Solaire[] getList() throws SQLException, Exception{
        Inserted[] ins = new Solaire().select(PGConnectivity.getCon()); 
        return Arrays.copyOf(ins,ins.length,Solaire[].class);
    }

    public static double[] getVariations(){
        return new double[] {0.5,0.5,0.5,0.75,0.75,1,1,1,0.75,0.75,0.5,0.5};
    }

    public String getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(String idEnergie) {
        this.idEnergie = idEnergie;
    }

    public Double getPuissance() {
        return puissance;
    }

    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

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

    

    
}
