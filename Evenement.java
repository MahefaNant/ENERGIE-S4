package event;

import draw.Case;
import draw.Chambre;
import draw.FunctDraw;
import energie.Energie;
import funct.Register;
import function.Function;
import graph.GraphAppareil;
import graph.GraphEnergie;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import time.Horloge;
import win.FormA;
import win.Tapis;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import appareil.Appareil;
import base.Database;
import construct.Construct;

import java.awt.*;

public class Evenement {
    Tapis tapis;
    Function f = new Function();
    FunctDraw fD = new FunctDraw();
    Construct construct = new Construct();

    public Evenement(Tapis tapis) {
        this. tapis = tapis;
    }

    public void dragApp(Case cas,Vector<GraphAppareil> A,Chambre chambre) {
        Point pos = new Point();
        cas.addMouseListener(new MouseListener() {

            public void mouseClicked(java.awt.event.MouseEvent e) {}
            public void mousePressed(java.awt.event.MouseEvent e) {
                pos.setLocation(e.getX(),e.getY());
            }
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if(chambre.Isinterieur(cas)==true) {
                    FormA fA = new FormA();
                    fA.valider.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent eb) {
                            String nom = fA.input[0].getText();
                            int puissance = f.toInt(fA.input[1].getText());
                            int I = f.toInt(fA.input[2].getText());
                            int F = f.toInt(fA.input[3].getText());
                            A.add(new GraphAppareil(cas.getX()+e.getX()-pos.x, cas.getY()+e.getY()-pos.y, 120, 90, new Appareil(nom, puissance, new Horloge(I, F))));
                            tapis.add(A.get(A.size()-1));
                            cas.setLocation(cas.xi, cas.yi);
                            fD.placeApp(A, chambre);
                            tapis.repaint();
                            fA.dispose();
                            //System.out.println(nom);
                        }
                    });
                    fA.annuler.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cas.setLocation(cas.xi, cas.yi);
                            tapis.repaint();
                            fA.dispose();
                        }

                    });
                } else cas.setLocation(cas.xi, cas.yi);
               
            }
            public void mouseEntered(java.awt.event.MouseEvent e) {}
            public void mouseExited(java.awt.event.MouseEvent e) {}
            
        });
        cas.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                cas.setLocation(cas.getX()+e.getX()-pos.x, cas.getY()+e.getY()-pos.y);
            }
            public void mouseMoved(java.awt.event.MouseEvent e) {}            
        });
    }

    public void dragEnergie(Case cas,Chambre chambre,Vector<GraphAppareil> appareils,Vector<GraphEnergie> energie,String nom) {
        Point pos = new Point();
        cas.addMouseListener(new MouseListener() {

            public void mouseClicked(java.awt.event.MouseEvent e) {}
            public void mousePressed(java.awt.event.MouseEvent e) {
                pos.setLocation(e.getX(),e.getY());
            }
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if(chambre.Isinterieur(cas)==true) {
                    energie.add(new GraphEnergie(0, 0, 120, 90, cas.E));
                    tapis.add(energie.get(energie.size()-1));
                    cas.setLocation(cas.xi, cas.yi);

                    Vector<Appareil> app = new Vector<>();
                    for(int i=0;i<appareils.size();i++)  {
                        app.add(appareils.get(i).A);  
                    }
                    Vector<Energie> energ = new Vector<>();
                    for(int i=0;i<energie.size();i++)  {
                        energ.add(energie.get(i).E);  
                    }

                    if(nom.equals("sol")) {
                        Vector<Energie> sol = construct.solaire();
                        Vector<Energie> Snec = construct.Enecessaire(sol, app);
                        Vector<Energie> newE = f.newEnec(Snec, sol, app);
                        int S_Snec = 0;
                        int S_S = 0;
                        for(int i=0;i<newE.size();i++) {
                            S_Snec+=newE.get(i).getPuissance();
                        }
                        for(int i=0;i<energ.size();i++) {
                            S_S+=energ.get(i).getPuissance();
                        }
                        fD.placeSolaire(energie,chambre);
                        if(S_Snec>S_S) JOptionPane.showMessageDialog(tapis, "Batterie insuffisant " +"\n - Votre puissance: "+ S_S+" w" +  "\n - puisance necessaire: " + S_Snec + "\n - puissance insuffisant: " + (S_Snec-S_S));
                        else if(S_Snec==S_S) JOptionPane.showMessageDialog(tapis, "Puisance parfaite");
                        else if(S_Snec<S_S) JOptionPane.showMessageDialog(tapis, "Energie suffisant \n -energie inutile: " + (S_S-S_Snec));
                    } 
                    if(nom.equals("bat")) {
                        Vector<Energie> bat = construct.batterie();
                        Vector<Energie> Bnec = construct.Enecessaire(bat, app);
                        int S_Bnec=0;
                        int S_B =0;
                        for(int i=0;i<Bnec.size();i++) {
                            S_Bnec+=Bnec.get(i).getPuissance();
                        }
                        for(int i=0;i<energ.size();i++) {
                            S_B+=energ.get(i).getPuissance();
                        }
                        fD.placeBatterie(energie, chambre);
                        if(S_Bnec>S_B) JOptionPane.showMessageDialog(tapis, "Batterie insuffisant " +"\n - Votre puissance: "+ S_B+" w" +  "\n - puisance necessaire: " + S_Bnec + "\n - puissance insuffisant: " + (S_Bnec-S_B));
                        else if(S_Bnec==S_B) JOptionPane.showMessageDialog(tapis, "Puisance parfaite");
                        else if(S_Bnec<S_B) JOptionPane.showMessageDialog(tapis, "Energie suffisant \n -energie inutile: " + (S_B-S_Bnec));
                        
                    } 


                    
                    tapis.repaint();
                } else cas.setLocation(cas.xi, cas.yi);
               
            }
            public void mouseEntered(java.awt.event.MouseEvent e) {}
            public void mouseExited(java.awt.event.MouseEvent e) {}
            
        });
        cas.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                cas.setLocation(cas.getX()+e.getX()-pos.x, cas.getY()+e.getY()-pos.y);
            }
            public void mouseMoved(java.awt.event.MouseEvent e) {}            
        });
    }

    public void Refresh(JButton refresh,Vector<GraphEnergie> solaire,Vector<GraphEnergie> batterie,Vector<GraphAppareil> appareils) {
            refresh.addActionListener(new ActionListener() {

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
                    for(int i=0;i<appareils.size();i++) {
                        appareils.get(i).removeAll();
                        appareils.get(i).setVisible(false);
                    }
                    solaire.clear();
                    batterie.clear();
                    appareils.clear();
                    tapis.repaint();
                }

            });
    }

    public void getPrixE(JButton prix,Vector<GraphEnergie> energies,String nom) {
        prix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int S =0;
                Vector<Energie> energ = new Vector<>();
                for(int i=0;i<energies.size();i++)  {
                    energ.add(energies.get(i).E);  
                }
                for(int i=0;i<energ.size();i++) {
                    S+=energ.get(i).getPrix();
                }
                JOptionPane.showMessageDialog(tapis, "Prix " + nom + " : " + S + " euro");
            }

        });

    }

    public void register(JButton register,Vector<GraphEnergie> solaire,Vector<GraphEnergie> batterie,Vector<GraphAppareil> appareils) throws SQLException{
        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Register");
                frame.setSize(500, 500);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
                JPanel pan = new JPanel();
                pan.setLayout(null);
                pan.setBounds(0, 0, frame.getWidth() ,frame.getHeight());
                pan.setBackground(Color.darkGray);


                JTextField nom = new JTextField("Nom");
                nom.setBounds(100, 100, 190, 50);
                pan.add(nom);

                JButton register = new JButton("Register");
                register.setBounds(100, 200, 100, 50);
                pan.add(register);

                try {
                    new Register(frame,register, solaire, batterie, appareils, nom);
                } catch (SQLException er) {
                    er.printStackTrace();
                }
                

                frame.add(pan);
                frame.setVisible(true);
                tapis.repaint();
            }

        });
    }

    public void getList(JButton list,Vector<GraphEnergie> solaire,Vector<GraphEnergie> batterie,Vector<GraphAppareil> appareils,Chambre c) {
        list.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("List");
                frame.setSize(500, 500);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
                JPanel pan = new JPanel();
                pan.setLayout(null);
                pan.setBounds(0, 0, frame.getWidth() ,frame.getHeight());
                pan.setBackground(Color.darkGray);

                Vector<JButton> labs = new Vector<>();
                Database d = new Database();
                try {
                    String[] ol = d.select("distinct(nom_user)", "registreA", null);
                    int sep = 0;
                    for(int i=0;i<ol.length;i++) {
                        JButton lab = new JButton(ol[i]);
                        lab.setBounds(10,10+sep,120,30);
                        lab.setFont(new java.awt.Font("Dialog", 0, 18));
                        labs.add(lab);
                        frame.add(lab);
                        sep+=30+10;
                    }

                    for(int i=0;i<labs.size();i++) {
                        int n = i;
                        labs.get(n).addActionListener(new ActionListener() {

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
                                for(int i=0;i<appareils.size();i++) {
                                    appareils.get(i).removeAll();
                                    appareils.get(i).setVisible(false);
                                }
                                solaire.clear();
                                batterie.clear();
                                appareils.clear();

                                f.getA(d, ol[n], appareils);
                                for(int i=0;i<appareils.size();i++) {
                                    tapis.add(appareils.get(i));
                                }
                                fD.placeApp(appareils, c);
                                
                                f.getS(d, ol[n], solaire);
                                for(int i=0;i<solaire.size();i++) {
                                    tapis.add(solaire.get(i));
                                }
                                fD.placeSolaire(solaire, c);

                                f.getB(d, ol[n], batterie);
                                for(int i=0;i<batterie.size();i++) {
                                    tapis.add(batterie.get(i));
                                }
                                fD.placeBatterie(batterie, c);

                                tapis.repaint();
                                frame.dispose();
                            }

                        });
                    }

                } catch (Exception er){
                    er.printStackTrace();
                }
                

                frame.add(pan);
                frame.setVisible(true);
                tapis.repaint();
            }

        });
        
    }
    
}
