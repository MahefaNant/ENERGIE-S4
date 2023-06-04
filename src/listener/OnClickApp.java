package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import frame.AppChoice;
import frame.Content;
import frame.LogInfo;

public class OnClickApp implements MouseListener {

    private AppChoice appChoice;
    private LogInfo dialog;

    public OnClickApp(AppChoice appChoice){
        this.appChoice = appChoice;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println("the apps have been clicked");
        Content parent = (Content) appChoice.getParent();
        dialog = new LogInfo(parent.getBoard());

        JDialog jd = new JDialog();
        jd.setSize(300,300);
        jd.add(dialog);
        jd.setVisible(true);
        jd.setLocationRelativeTo(null);   
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
