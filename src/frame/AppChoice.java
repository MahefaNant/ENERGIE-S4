package frame;

import javax.swing.JPanel;
import javax.swing.border.Border;
import inserted.Materiel;

import javax.swing.BorderFactory;


public class AppChoice extends JPanel{

    private boolean moving;
    private int posX;
    private int posY;
    
    public AppChoice(Content g){
        initUI(g);
    }

    private void initUI(Content g){
        setLayout(null);
        setBounds(800, 0, 100, 500);
        g.add(this);

        Border blackline = BorderFactory.createTitledBorder("Appareil");
        setBorder(blackline);
        drawList();
    }

    private void drawList(){
       new Materiel().doDrawing(this, 25, 50);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    
}
