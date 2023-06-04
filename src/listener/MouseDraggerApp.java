package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import frame.AppChoice;
import frame.Content;
import frame.LogInfo;

import java.awt.Point;
import java.awt.Rectangle;


public class MouseDraggerApp implements MouseMotionListener,MouseListener{

    private AppChoice parent;
    private Content g;

    public MouseDraggerApp (AppChoice parent){
        this.parent = parent;
        g = (Content)parent.getParent();
    }
    
    private Point convertPoint(Point pt){
        return SwingUtilities.convertPoint(parent,pt,g.getGestion().getGlassPane());
                                            
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
        g.getGestion().getGlassPane().setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        if(parent.isMoving()){
            System.out.println("it is released");

           Point pt = convertPoint(e.getPoint());
            parent.setMoving(false);
            g.getGestion().getGlassPane().setVisible(false);
            if(new Rectangle(500,0,300,300).contains(pt)){
                System.out.println("it contains");
                
                JDialog jd = new JDialog();
                jd.setSize(300,300);
                jd.add(new LogInfo(g.getBoard()));
                jd.setVisible(true);
                jd.setLocationRelativeTo(null);  
            }
        }
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        if(parent.isMoving()){
            System.out.println("it is dragged");
            Point contentPoint = convertPoint(e.getPoint());
            parent.setPosX(contentPoint.x);
            parent.setPosY(contentPoint.y + 25);
            g.getGestion().getGlassPane().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

  
    
}
