package draw;

import javax.swing.plaf.ColorUIResource;

import draw.Case;
import draw.Draw;

public class Chambre extends Draw{

    public Chambre(int x, int y, int w, int h, ColorUIResource color, String form) {
        super(x, y, w, h, color, form);
        //TODO Auto-generated constructor stub
    }

    public boolean Isinterieur(Case cas) {
        if(cas.getX()>x && cas.getX()+cas.getWidth() < x+w)
            if(cas.getY() >y && cas.getY()+cas.getHeight() <y+h) return true;
        return false;
    }
    
}
