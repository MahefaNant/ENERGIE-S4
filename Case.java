package draw;

import javax.swing.*;

import appareil.Appareil;
import energie.Energie;

import java.awt.*;

public class Case extends JLabel{
    public int xi,yi;
    public int x,y,w,h;

    public Appareil A;
    public Energie E;

    public Case(int x,int y,int w,int h) {
        this.x = x;this.y=y;this.w = w;this.h=h;
        this.xi = x;
        this.yi = y;
        this.setBounds(x, y, w, h);
        this.setOpaque(true);
    }

    public boolean collision(Case case1) {
        return case1.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

}
