package graph;

import draw.Case;
import energie.Energie;

import java.awt.*;

import javax.swing.*;

public class GraphEnergie extends Case {

    public JLabel[] text = new JLabel[3];

    public GraphEnergie(int x, int y, int w, int h,Energie E) {
        super(x, y, w, h);
        //setBackground(Color.white);
        this.A = null;
        this.E = E;

        text[0] = new JLabel("Puissance: " + E.getPuissance()+" Wh",JLabel.CENTER);
        text[1] = new JLabel("Prix: " + E.getPrix() +" Euros",JLabel.CENTER);
        text[2] = new JLabel(E.getNom(),JLabel.CENTER);

        int sep = 0;
        for(int i=0;i<text.length;i++) {
            text[i].setOpaque(true);
            text[i].setBounds(5,0+ sep, w, 30);
            this.add(text[i]);
            sep+=20;
        }
    }
    
}
