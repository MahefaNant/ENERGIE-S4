package tool;

public class Status {
    private String etat;
    private double resultat;

    public Status(String etat,double resultat){
        this.etat = etat;
        this.resultat = resultat;
    }

    @Override
    public String toString(){
        String res = (resultat < 0) ? "surdimensionne" : "sousdimensionne";
       return etat+" votre estimation est "+res+" de "+((resultat < 0) ? resultat * (-1) : resultat);
    }
    
}
