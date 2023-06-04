package win;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import construct.Construct;

import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

import draw.Case;
import draw.Chambre;
import draw.Draw;
import draw.FunctDraw;
import energie.Energie;
import event.Evenement;
import funct.Proposer;
import graph.GraphAppareil;
import graph.GraphEnergie;

public class Tapis extends JLabel{
    int blockSize = 30;

    Evenement event = new Evenement(this);
    Construct construct = new Construct();
    FunctDraw functD = new FunctDraw();
    Vector<Draw> draws = new Vector<>();

    Vector<GraphEnergie> GraphSolaire = new Vector<>();
    Vector<GraphEnergie> GraphBatterie = new Vector<>();
    Vector<GraphAppareil> GraphA = new Vector<>();

    Vector<GraphEnergie> solaire = new Vector<>();
    Vector<GraphEnergie> batterie = new Vector<>();
    Vector<GraphAppareil> appareils = new Vector<>();
    Chambre chambre ;

    JButton refresh = null;
    JButton  prixS = null;
    JButton  prixB = null;
    JButton  register = null;
    JButton  list = null;
    

    public Tapis(int x, int y, int w, int h, ColorUIResource back) {
        this.setBounds(x, y, w, h);
        this.setOpaque(true);
        this.setBackground(back);
        chambre = new Chambre(15*blockSize+10, 4*blockSize+10, 27*blockSize-20,22*blockSize-20 , new ColorUIResource(Color.white), "rect");
        functD.addGraphE(this,construct.solaire(),GraphSolaire,blockSize+5);
        functD.addGraphE(this,construct.batterie(),GraphBatterie,8*blockSize+5);
        functD.addGraphA(this, GraphA, 0);

        for(GraphAppareil gA : GraphA) event.dragApp(gA,appareils,chambre);
        for(GraphEnergie gE : GraphSolaire) event.dragEnergie(gE,chambre, appareils, solaire  ,"sol");
        for(GraphEnergie gE : GraphBatterie) event.dragEnergie(gE,chambre,appareils, batterie,"bat");
        new Proposer(this, appareils,solaire,batterie,chambre);

        refresh = new JButton("Refresh");
        refresh.setBounds(22*blockSize, 26*blockSize, 150, 50);
        this.add(refresh);
        event.Refresh(refresh,solaire,batterie,appareils);

        prixS = new JButton("Prix panneaux");
        prixS.setBounds(28*blockSize, 26*blockSize, 150, 50);
        this.add(prixS);
        event.getPrixE(prixS, solaire,"Panneau");

        prixB = new JButton("Prix batterie");
        prixB.setBounds(34*blockSize, 26*blockSize, 150, 50);
        this.add(prixB);
        event.getPrixE(prixB, batterie,"Batterie");

        register= new JButton("REGISTER");
        register.setBounds(40*blockSize, 26*blockSize, 150, 50);
        this.add(register);
        try {
            event.register(register,solaire,batterie,appareils);;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list= new JButton("LIST");
        list.setBounds(40*blockSize, 2*blockSize, 150, 50);
        this.add(list);
        event.getList(list, solaire, batterie, appareils,chambre);
        

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        functD.drawLine(draws, g2d);
        chambre.paint(g2d);
    }
}
