package graph;

import appareil.Appareil;
import draw.Case;
import java.awt.*;

import javax.swing.JLabel;

public class GraphAppareil extends Case{

    public JLabel[] text = new JLabel[3];

    public GraphAppareil(int x, int y, int w, int h,Appareil A) {
        super(x, y, w, h);
        this.E = null;
        this.A = A;
        //TODO Auto-generated constructor stub

        //JLabel[] text = new JLabel[3];
        if(A.getPuissance()==0) text[0] = new JLabel ("Puissance: ???",JLabel.CENTER);
        else text[0] =  new JLabel("Puissance: " + A.getPuissance()+" Wh",JLabel.CENTER);

        if(A.getPuissance()==0)  text[1] = new JLabel("Time: ???",JLabel.CENTER);
        else text[1] = new JLabel("Time: " + A.getTime().getI()+":0 à " + A.getTime().getF()+":0",JLabel.CENTER);

        if(A.getPuissance()==0) text[2] = new JLabel("Appareil: ???",JLabel.CENTER);
        else text[2] = new JLabel(A.getNom(),JLabel.CENTER);

        int sep = 0;
        for(int i=0;i<text.length;i++) {
            text[i].setOpaque(true);
            text[i].setBounds(5,0+ sep, w, 30);
            this.add(text[i]);
            sep+=20;
        }
    }


    /*public void paint(Graphics2D g) {
        g.setColor(Color.RED);
        if(A.getPuissance()==0) g.drawString("Puissance: ???", x+5, y+10);
        else g.drawString("Puissance: " + A.getPuissance()+" Wh", x+5, y+10);
        if(A.getTime()==null) g.drawString("Time: ???" , x+5, y+30);
        else g.drawString("Time: " + A.getTime().getI()+":0 à " + A.getTime().getF()+":0" , x+5, y+30);
        if(A.getNom()==null) g.drawString("Appareil: ???", x+5, y+50);
        else g.drawString(A.getNom(), x+5, y+50);
    }*/
    
}
