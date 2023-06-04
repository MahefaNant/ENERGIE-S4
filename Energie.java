package energie;

import base.Database;
import time.Horloge;

public class Energie extends Database{
    private String nom;
    private int puissance;//Kwh
    private float prix;//Ar
    private Horloge time;
    private Horloge[] cumul_time;
    private int[] pourcent ={50,75,100,100,75,50}; 

    public Energie(int puissance,float prix,Horloge time) {
        this.puissance = puissance;
        this.prix = prix;
        this.time = time;

        this.cumul_time = new Horloge[6];
        cumul_time[0] = new Horloge(6, 8);
        cumul_time[1] = new Horloge(8, 10);
        cumul_time[2] = new Horloge(10, 12);
        cumul_time[3] = new Horloge(12, 14);
        cumul_time[4] = new Horloge(14, 16);
        cumul_time[5] = new Horloge(16, 18);
    }

    ///geters
    public String getNom() {
        return this.nom;
    }
    public int getPuissance() {
        return this.puissance;
    }
    public float getPrix() {
        return this.prix;
    }
    public Horloge getTime() {
        return this.time;
    }
    public Horloge[] getCumTime() {
        return this.cumul_time;
    }
    public int[] getPourcent() {
        return this.pourcent;
    }
    public int pourcentPuiss(int pourcent) {
        return (pourcent*this.puissance)/100;
    }

    //seters
    public void setNom(String nom) {
        this.nom  = nom;
    }
    public void setpPuissance(int puissance) {
        this.puissance  = puissance;
    }
    public void setPrix(float prix) {
        this.prix  = prix;
    }
    public void setTime(Horloge time) {
        this.time  = time;
    }

}
