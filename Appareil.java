package appareil;

import time.Horloge;

public class Appareil {
    private String nom;
    private int puissance;//Kwh
    private Horloge time;

    public Appareil(String nom,int puissance,Horloge time) {
        this.puissance = puissance;
        this.time = time;
        this.nom = nom;
    }

    //geters
    public int getPuissance() {
        return this.puissance;
    }
    public Horloge getTime() {
        return this.time;
    }
    public String getNom() {
        return this.nom;
    }

    //seters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setpPuissance(int puissance) {
        this.puissance  = puissance;
    }
    public void setTime(Horloge time) {
        this.time  = time;
    }

}
