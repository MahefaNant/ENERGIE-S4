package funct;

import java.util.Vector;

import javax.swing.*;

import appareil.Appareil;
import construct.Construct;
import draw.Chambre;
import draw.FunctDraw;
import energie.Energie;
import function.Function;
import graph.GraphAppareil;
import graph.GraphEnergie;
import win.Tapis;

import java.awt.*;
import java.awt.event.*;

public class Proposer extends JButton{
    int blockSize = 30;
    Construct construct = new Construct();
    Function f = new Function();
    FunctDraw fD = new FunctDraw();
    public Proposer(Tapis tapis,Vector<GraphAppareil> appareils,Vector<GraphEnergie> solaire,Vector<GraphEnergie> batterie,Chambre chambre) {
        Vector<Energie> sol = construct.solaire();
        Vector<Energie> bat = construct.batterie();
        Vector<Appareil> app = f.getApp(appareils);
        this.setBounds(16*blockSize, 26*blockSize, 150, 50);
        this.setText("PROPOSER");
        tapis.add(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<solaire.size();i++) {
                    solaire.get(i).removeAll();
                    solaire.get(i).setVisible(false);
                } 
                for(int i=0;i<batterie.size();i++) {
                    batterie.get(i).removeAll();
                    batterie.get(i).setVisible(false);
                }
                solaire.clear();
                batterie.clear();
                tapis.repaint();
                Vector<Appareil> app = new Vector<>();
                for(int i=0;i<appareils.size();i++)  {
                    app.add(appareils.get(i).A);  
                }
                    
                Vector<Energie> Snec = construct.Enecessaire(sol, app);
                Vector<Energie> Bnec = construct.Enecessaire(bat, app);
                int s=0;
                for(int i=0;i<Bnec.size();i++) {
                    s+=Bnec.get(i).getPuissance();
                }
                Vector<Energie> newE = f.newEnec(Snec, sol, app);
                for(int i=0;i<newE.size();i++) solaire.add(new GraphEnergie(0, 0, 120, 90, newE.get(i)));
                for(int i=0;i<Bnec.size();i++) batterie.add(new GraphEnergie(0, 0, 120, 90, Bnec.get(i)));
                for(int i=0;i<solaire.size();i++) tapis.add(solaire.get(i));
                for(int i=0;i<batterie.size();i++) tapis.add(batterie.get(i));
                fD.placeSolaire(solaire, chambre);
                fD.placeBatterie(batterie, chambre);
                JOptionPane.showMessageDialog(tapis, "Somme prix Panneau: " + f.sommePrix(newE) + "\n" + "Somme prix Batterie: " + f.sommePrix(Bnec));
                tapis.repaint();
            }

        });
    }
}
