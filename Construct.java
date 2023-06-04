package construct;

import java.util.Vector;

import appareil.Appareil;

import java.util.*;

import base.Database;
import energie.Energie;
import function.Function;
import time.Horloge;

public class Construct extends Database{
    Function f = new Function();

    Horloge timeSolaire = new Horloge(6, 18);
    Horloge timeBatterie = new Horloge(18, 23);
    
    public Vector<Energie> solaire() {
        Vector<Energie> sol = new Vector<Energie>();

        String[] nom = select("nom_solaire", "solaire", null);
        String[] puissance = select("puissance", "solaire", null);
        String[] prix = select("prix", "solaire", null);


        for(int i=0;i<nom.length;i++) {
            sol.add(new Energie(f.toInt(puissance[i]), f.toFloat(prix[i]), timeSolaire));
            sol.get(i).setNom("solaire");
        }
        return sol;
    }

    public Vector<Energie> batterie() {
        Vector<Energie> sol = new Vector<Energie>();

        String[] nom = select("nom_batterie", "batterie", null);
        String[] puissance = select("puissance", "batterie", null);
        String[] prix = select("prix", "batterie", null);

        for(int i=0;i<nom.length;i++) {
            sol.add(new Energie(f.toInt(puissance[i]), f.toFloat(prix[i]), timeBatterie));
            sol.get(i).setNom("batterie");
        }
        return sol;
    }

    public Vector<Energie> reorderPuissanceE(Vector<Energie> E,String order) {
        Vector<Energie> res = new Vector<Energie>();
        Vector<Integer> puis = new Vector<Integer>();
        for(int i=0;i<E.size();i++) puis.add(E.get(i).getPuissance());
        Vector<Integer> nstore = new Vector<Integer>(puis);
        if(order=="ASC") Collections.sort(puis);
        else if(order=="DESC") Collections.sort(puis,Collections.reverseOrder());
        int[] indexes = new int[puis.size()];
        for (int i = 0; i < puis.size(); i++) indexes[i] = nstore.indexOf(puis.get(i));
        for(int i=0;i<indexes.length;i++) for(int j=0;j<E.size();j++) if(j==indexes[i]) res.add(E.get(j));
        return res;
    }

    public Vector<Energie> Enecessaire(Vector<Energie> E,Vector<Appareil> appareils) {
        Boolean verif = true;
        int Spuiss_Energ = 0;
        Vector<Energie> res = new Vector<Energie>();
        Vector<Energie> ascE = reorderPuissanceE(E, "ASC");
        Vector<Energie> descE = reorderPuissanceE(E, "DESC");
        int Spuiss_App = f.Spuiss_App(E.get(0).getTime(), appareils);
        for(int i=0;i<E.size();i++) if(ascE.get(i).getPuissance()>=Spuiss_App) {
            res.add(ascE.get(i));
            verif =false;
            break;
        } 

        if(verif==true) for(int i=0;i<E.size();i++) if(Spuiss_Energ<Spuiss_App){
            if(Spuiss_Energ+descE.get(i).getPuissance() >Spuiss_App) {
                Spuiss_Energ+=descE.get(i).getPuissance();
                res.add(descE.get(i));
            } else if(Spuiss_Energ+descE.get(i).getPuissance() <=Spuiss_App) {
                Spuiss_Energ+=descE.get(i).getPuissance();
                res.add(descE.get(i));
                i=0;
            }
        }
        return res;
    }

    public Vector<Energie> addCumulE(Vector<Energie> E,Vector<Appareil> appareils) {
        Vector<Energie> res = Enecessaire(E, appareils);
        Vector<Energie> Enec = Enecessaire(E, appareils);
        Vector<Energie> ascE = reorderPuissanceE(E, "ASC");
        Vector<Energie> Enecessaire = Enecessaire(E, appareils);
        for(int i=0;i<Enec.size();i++) {
            //f.addEnecess(res,ascE, res.get(i), Enec, appareils);
        }
        //f.addEnecess(ascE, res, appareils);
        return res;
    }

}
