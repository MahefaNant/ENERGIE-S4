package listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import frame.Content;
import frame.EngChoice;
import inserted.Energie;

public class MouseDraggerEng implements MouseMotionListener,MouseListener{

    private Energie eng;
    private EngChoice parent;
    private Content g;
    private int addY;

    public MouseDraggerEng (Energie eng,EngChoice parent,int y){
        this.eng = eng;
        this.parent = parent;
        g = (Content)parent.getParent();
        this.addY = y;
    }
    
    private Point convertPoint(Point pt){
        Point tmp = SwingUtilities.convertPoint(parent,pt,g.getGestion().getGlassPane());
        tmp.y = tmp.y + addY;
        return tmp;
                                            
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(parent.isMoving()){
            Point contentPoint = convertPoint(e.getPoint());
            parent.setPosX(contentPoint.x);
            parent.setPosY(contentPoint.y);
            g.getGestion().getGlassPane().repaint();

        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stubp
        System.out.println("it is pressed");
        parent.setMoving(true);
        parent.setChoice(eng);
        g.getGestion().getGlassPane().setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        if(parent.isMoving()){
            System.out.println("it is released");

           Point pt = convertPoint(e.getPoint());
            parent.setMoving(false);
            parent.setChoice(null);
            g.getGestion().getGlassPane().setVisible(false);
            if(new Rectangle(200,0,300,300).contains(pt)){
                System.out.println("it contains");
                g.getBoard().addEnergy(eng);
                g.getBoard().setTextLog("Source energie ajoute");
            }
        }
        
    }
    
}
