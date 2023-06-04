package function;

import java.util.TreeSet;
import java.util.Vector;

import appareil.Appareil;
import base.Database;
import construct.Construct;
import energie.Energie;
import graph.GraphAppareil;
import graph.GraphEnergie;
import time.Horloge;

public class Function {
    public int toInt(String nb) {
        return Integer.parseInt(nb);
    }

    public float toFloat(String nb) {
        return Float.parseFloat(nb);
    }

    public Vector<Appareil> appInObj(Horloge time,Vector<Appareil> appareils) {
        Vector<Appareil> res = new Vector<Appareil>();//return
        for(int i=0;i<appareils.size();i++) {
            if(appareils.get(i).getTime().getDurer(time)!=0)
                res.add(appareils.get(i));
        }
        return res;
    }

    public Vector<Appareil> appInSol(Energie solaire,Vector<Appareil> appareils) {
        return this.appInObj(solaire.getTime(), appareils);
    }
    public Vector<Appareil> appInBat(Energie batterie,Vector<Appareil> appareils) {
        return this.appInObj(batterie.getTime(), appareils);
    }

    public int Spuiss_App(Horloge time,Vector<Appareil> appareils) {
        int res = 0;
        Vector<Appareil> appInObj = appInObj(time, appareils);
        for(int i =0;i<appInObj.size();i++) {
            res+=appInObj.get(i).getPuissance()*appInObj.get(i).getTime().getDurer(time);
        }
        return res;
    }

    public boolean[] SpuissEperH(Energie E ,Vector<Appareil> appareils,int[] reste) {
        boolean[] res = {false,false,false,false,false,false};
        for(int i=0;i<E.getCumTime().length;i++) {
            Horloge[] ETs = E.getCumTime();
            int SpA = Spuiss_App(ETs[i], appareils);
            if(E.pourcentPuiss(E.getPourcent()[i])>=SpA) {
                res[i] = true;
                reste[i] = 0;
            } else {
                reste[i] = SpA - E.pourcentPuiss(E.getPourcent()[i]);
            }
        }
        /////somme puissance E per Horloge
        return res;
    }

    public void resteE(Vector<Energie> e,int reste,Horloge time) {
        
    }

    public void addEnecess(Vector<Energie> res,Vector<Energie> ascE,Energie energ,Vector<Energie> Enecess,Vector<Appareil> appareils) {
        int [] reste = new int[6];
        boolean[] Sp = SpuissEperH(energ, appareils,reste);
        int puiss = 0;
        for(int i=0;i<Sp.length;i++) {
            puiss = 0;
            if(Sp[i]==false) {
                for(int j=0;j<ascE.size();j++) {
                    if(j==ascE.size()-1 && puiss<reste[i]){
                        res.add(ascE.get(j));
                        puiss+= ascE.get(j).pourcentPuiss(ascE.get(j).getPourcent()[i]);
                        j=0;
                    } else
                    if(puiss + ascE.get(j).pourcentPuiss(ascE.get(j).getPourcent()[i])>=reste[i]) {
                        res.add(ascE.get(j));
                        puiss+= ascE.get(j).pourcentPuiss(ascE.get(j).getPourcent()[i]);
                        break;
                    }
                }
            }
        }
    }

    public int pourcent(Horloge time) {
        if(time.isEqual(new Horloge(6, 8))  || time.equals(new Horloge(16, 18))) return 50;
        else if(time.isEqual(new Horloge(8, 10))|| time.isEqual(new Horloge(14, 16))) return 75;
        else if(time.isEqual(new Horloge(10, 12))|| time.isEqual(new Horloge(12, 14))) return 100;
        else if(time==null) return 100;
        return 100;
    }

    public Vector<Horloge> timeUtile(Vector<Appareil> appareils) {
        Vector<Horloge> res = new Vector<>();
        Vector<Integer> I = new Vector<>();
        Vector<Integer> F = new Vector<>();
        Energie E = new Energie(0, 0, new Horloge(6, 18));
        for(int j=0;j<E.getCumTime().length;j++)
            for(int i=0;i<appareils.size();i++) 
                if(appareils.get(i).getTime().getDurer(E.getCumTime()[j])!=0) 
                    res.add(E.getCumTime()[j]);
        for(int i=0;i<res.size();i++) {
            I.add(res.get(i).getI());
            F.add(res.get(i).getF());
        }
        TreeSet<Integer> tI= new TreeSet<Integer>(I);
        TreeSet<Integer> tF= new TreeSet<Integer>(I);
        res.clear();
        for(int i=0;i<tI.size();i++) {
            res.add(new Horloge(I.get(i),F.get(i)));
        }
        return res;
    }

    public int Spuiss_Energ(Vector<Energie> Enec,Horloge time) {
        int res =0;
        for(int i=0;i<Enec.size();i++) {
            if(time==null) res+=Enec.get(i).getPuissance();
            else
            res+=Enec.get(i).pourcentPuiss(pourcent(time));
        }
        return res;
    }

    public Vector<Energie> addEnec(int rest,Horloge time,Vector<Energie>E)  {
        boolean verif =true;
        int somme = 0;
        Construct c = new Construct();
        Vector<Energie> res = new Vector<>();
        Vector<Energie> ascE = c.reorderPuissanceE(E, "ASC");
        Vector<Energie> descE = c.reorderPuissanceE(E, "DESC");
        for(int i=0;i<ascE.size();i++) {
            int pE = ascE.get(i).pourcentPuiss(pourcent(time));
            
            if(pE>=rest) {
                res.add(ascE.get(i));
                verif = false;
                break;
            }
        }
        //if(verif==true) System.out.println("OK");
        if(verif==true) for(int i=0;i<E.size();i++) {
            int pE = descE.get(i).pourcentPuiss(pourcent(time));
            if(somme<rest) {
                if(somme+pE >rest) {
                    somme+=pE;
                    res.add(descE.get(i));
                } else
                if(somme+pE<=rest) {
                    somme+=pE;
                    res.add(descE.get(i));
                    i=0;
                }
            }
        }
        return res;
    }

    public Vector<Energie> newEnec(Vector<Energie> Enec,Vector<Energie> E  ,Vector<Appareil> appareils){
        Vector<Energie>res = new Vector<>();
        Vector<Horloge> timeUtile = timeUtile(appareils);
        for(int i=0;i<timeUtile.size();i++) {
            int SpA = Spuiss_App(timeUtile.get(i), appareils);
            int SpE = Spuiss_Energ(Enec, timeUtile.get(i));
            if(SpE<SpA) {
                int reste = SpA-SpE;
                Enec.addAll(addEnec(reste,timeUtile.get(i) , E));
                //System.out.println(reste);
            }
        }
        return Enec;
    }

    public Vector<Appareil> getApp(Vector<GraphAppareil> gA) {
        Vector<Appareil> res = new Vector<>();
        for(int i =0 ;i< gA.size();i++) {
            res.add(gA.get(i).A);
        }
        return res;
    }

    public float sommePrix(Vector<Energie> E) {
        float res =0;
        for(int i=0;i<E.size();i++) {
            res+=E.get(i).getPrix();
        }
        return res;
    }

    public void getA(Database d,String user,Vector<GraphAppareil> appareils) {
        try {
            String[] nom = d.select("appareil_nom", "registreA", " where nom_user='"+user+"'");
            String[] puiss = d.select("appareil_puisance", "registreA", " where nom_user='"+user+"'");
            String[] I = d.select("appareil_I", "registreA", " where nom_user='"+user+"'");
            String[] F = d.select("appareil_F", "registreA", " where nom_user='"+user+"'");
            Vector<Appareil> app = new Vector<>();
            for(int i=0;i<nom.length;i++) {
                app.add(new Appareil(nom[i], toInt(puiss[i]), new Horloge(toInt(I[i]),toInt(F[i]))));
            }

            for(int i=0;i<app.size();i++) {
                appareils.add(new GraphAppareil(0, 0, 120, 90, app.get(i)));
            }
            
        } catch (Exception e) {
            
        }
    }

    public void getS(Database d,String user,Vector<GraphEnergie> solaire) {
        try {
            String[] puiss = d.select("solaire_puissance", "registerS", " where nom_user='"+user+"'");
            String[] prix = d.select("solaire_prix", "registerS", " where nom_user='"+user+"'");
            Vector<Energie> energ = new Vector<>();
            for(int i=0;i<puiss.length;i++) {
                energ.add(new Energie(toInt(puiss[i]), toFloat(prix[i]), new Horloge(6,18)));
            }

            for(int i=0;i<energ.size();i++) {
                energ.get(i).setNom("solaire");
                solaire.add(new GraphEnergie(0, 0, 120, 90, energ.get(i)));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void getB(Database d,String user,Vector<GraphEnergie> batterie) {
        try {
            String[] puiss = d.select("batterie_puissance", "registerB", " where nom_user='"+user+"'");
            String[] prix = d.select("batterie_prix", "registerB", " where nom_user='"+user+"'");
            Vector<Energie> energ = new Vector<>();
            for(int i=0;i<puiss.length;i++) {
                energ.add(new Energie(toInt(puiss[i]), toFloat(prix[i]), new Horloge(6,18)));
            }

            for(int i=0;i<energ.size();i++) {
                energ.get(i).setNom("batterie");
                batterie.add(new GraphEnergie(0, 0, 120, 90, energ.get(i)));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}
