package draw;

import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.util.ArrayList;

public class Draw {
    public int x,y,w,h;
    String form;
    int blocksize = 30;
    public ColorUIResource color,border;
    public boolean exist = true;
    Graphics2D g2d;
    public Draw(int x,int y,int w,int h,ColorUIResource color,String form) {
        this.x = x;
        this.y = y;
        this.w =w;
        this.h =h;
        this.color = color;
        this.form = form;
    }

    public void paint(Graphics2D g2d) {
        this.g2d=g2d;
        g2d.setColor(color);
        if(form.equals("Frect")) g2d.fillRect(x, y, w, h);
        if(form.equals("rect")) g2d.drawRect(x, y, w, h);
        
    }

}
