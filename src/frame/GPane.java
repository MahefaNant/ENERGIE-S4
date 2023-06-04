package frame;

import javax.swing.JComponent;

import inserted.Materiel;

import java.awt.Graphics;


public class GPane extends JComponent {

    private EngChoice batts;
    private EngChoice sols;
    private AppChoice app;
    
    
    public GPane (Gestion gestion){
        Content content = gestion.getContent();
        batts = content.getBatts();
        sols = content.getSols();
        app = content.getChoice();
    }

    @Override
    public void paintComponent(Graphics g){
        if(batts.isMoving()){
            batts.getChoice().loadImage().paintIcon(this, g, batts.getPosX(), batts.getPosY());   
        }
        if(sols.isMoving()){
            sols.getChoice().loadImage().paintIcon(this, g, sols.getPosX(), sols.getPosY()); 
        }
        if(app.isMoving()){
            new Materiel().loadImage().paintIcon(this, g, app.getPosX(), app.getPosY());
        }
    }

 

    
}
