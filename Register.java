package funct;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import appareil.Appareil;
import base.Database;
import energie.Energie;
import graph.GraphAppareil;
import graph.GraphEnergie;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class Register extends Database{
        public Register(JFrame frame,JButton register,Vector<GraphEnergie> solaire,Vector<GraphEnergie> batterie,Vector<GraphAppareil> appareils,JTextField nom) throws SQLException{
            register.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Vector<Appareil> app = new Vector<>();
                    for(int i=0;i<appareils.size();i++)  {
                        app.add(appareils.get(i).A);  
                    }

                    Vector<Energie> sol = new Vector<>();
                    for(int i=0;i<solaire.size();i++)  {
                        sol.add(solaire.get(i).E);  
                    }

                    Vector<Energie> bat = new Vector<>();
                    for(int i=0;i<batterie.size();i++)  {
                        bat.add(batterie.get(i).E);  
                    }



                    for(int i=0;i<app.size();i++) {
                        String[] values = new String[6];
                        values[0] = "seq_registerA.nextval";
                        values[1] = "'"+ nom.getText() +"'";
                        values[2] = "'"+app.get(i).getNom()+"'";
                        values[3] = app.get(i).getPuissance()+"";
                        values[4] = app.get(i).getTime().getI()+"";
                        values[5] = app.get(i).getTime().getF()+"";
                        try {
                            insert("registreA", values);
                            
                        } catch (Exception er) {
                            System.out.println("ASd");
                            er.printStackTrace();
                        }
                    }

                    for(int i=0;i<sol.size();i++) {
                        String[] values = new String[4];
                        values[0] = "seq_registerS.nextval";
                        values[1] = "'"+nom.getText()+"'";
                        values[2] = sol.get(i).getPuissance()+"";
                        values[3] = sol.get(i).getPrix()+"";
                        try {
                            insert("registerS", values);
                        } catch (Exception er) {
                            er.printStackTrace();
                        }
                        
                    }

                    for(int i=0;i<bat.size();i++) {
                        String[] values = new String[4];
                        values[0] = "seq_registerB.nextval";
                        values[1] = "'"+nom.getText()+"'";
                        values[2] = bat.get(i).getPuissance()+"";
                        values[3] = bat.get(i).getPrix()+"";
                        try {
                            insert("registerB", values);
                        } catch (Exception er) {
                            er.printStackTrace();
                        }
                        
                    }
                    frame.dispose();   
                }

            });
        }
}
