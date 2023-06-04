package frame;

import javax.swing.BorderFactory;

import javax.swing.JPanel;
import javax.swing.border.Border;

import inserted.Batterie;
import inserted.Energie;
import inserted.Solaire;


public class EngChoice extends JPanel {

    private String titre;
    private Energie[] list;
    private Energie choice;
    private boolean moving;
    private int posX;
    private int posY;

    public EngChoice(String titre, Content gestion) {
        this.titre = titre;
        setList();
        initUI(gestion);
    }

    private void setList() {
        try {
            list = (titre.equals("Batterie")) ? Batterie.getList() : Solaire.getList();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initUI(Content g) {
        int x = (titre.equals("Batterie")) ? 0 : 100;
        setBounds(x, 0, 100, 500);
        g.add(this);

        Border blackline = BorderFactory.createTitledBorder(titre);
        setBorder(blackline);
        drawList();
    }

    private void drawList() {
        setLayout(null);
        int y = 25;

        for (int i = 0; i < list.length; i++, y += 50) {
            list[i].doDrawing(this, 30, y);
        }
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

    public Energie getChoice() {
        return choice;
    }

    public void setChoice(Energie choice) {
        this.choice = choice;
    }

    


}
