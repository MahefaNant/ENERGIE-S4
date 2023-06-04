package draw;


import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.plaf.ColorUIResource;

import appareil.Appareil;

import javax.swing.*;

import construct.Construct;
import draw.Case;
import draw.Draw;
import energie.Energie;
import graph.GraphAppareil;
import graph.GraphEnergie;

public class FunctDraw {
    int blocksize = 30;

    public void drawLine(Vector<Draw> draw,Graphics2D g2d) {
        draw.add(new Draw(blocksize, 4*blocksize, 6*blocksize,22*blocksize , new ColorUIResource(Color.white), "rect"));//0
        draw.add(new Draw(8*blocksize, 4*blocksize, 6*blocksize,22*blocksize , new ColorUIResource(Color.white), "rect"));//1

        draw.add(new Draw(15*blocksize, 4*blocksize, 27*blocksize,22*blocksize , new ColorUIResource(Color.white), "rect"));//2
        
        draw.add(new Draw(43*blocksize, 4*blocksize, 6*blocksize,22*blocksize , new ColorUIResource(Color.white), "rect"));//3
        for(int i=0;i<draw.size();i++) draw.get(i).paint(g2d);
    }


    public void addGraphE(JLabel lab,Vector<Energie> E  ,Vector<GraphEnergie> GraphE,int x) {
        Construct construct = new Construct();
        int sep =0;
        for(int i=0;i<E.size();i++) {
            GraphE.add(new GraphEnergie(x, 5*blocksize+sep, 160, 90, E.get(i)));
            sep+=blocksize+blocksize*3;
        }
        for(GraphEnergie gE: GraphE) lab.add(gE);
    }

    public void addGraphA(JLabel lab,Vector<GraphAppareil> GraphA,int x) {
        GraphA.add(new GraphAppareil(43*blocksize+5, 5*blocksize, 160, 90, new Appareil(null, 0, null)));
        for(GraphAppareil gA: GraphA) lab.add(gA);
    }

    public void placeApp(Vector<GraphAppareil> cases,Chambre c) {
        int sep =0;
        for(int i =0;i<cases.size();i++) {
            cases.get(i).setLocation(c.x+c.w-cases.get(i).w-10, c.y+30 + sep);
            sep += cases.get(i).h + 10;
        }
    }

    public void placeSolaire(Vector<GraphEnergie>cases,Chambre c) {
        int sep =0;
        for(int i =0;i<cases.size();i++) {
            cases.get(i).setLocation(c.x+10, c.y+30 + sep);
            sep += cases.get(i).h + 10;
        }
    }

    public void placeBatterie(Vector<GraphEnergie>cases,Chambre c) {
        int sep =0;
        for(int i =0;i<cases.size();i++) {
            cases.get(i).setLocation(c.x+cases.get(i).w + 30, c.y+30 + sep);
            sep += cases.get(i).h + 10;
        }
    }

}
