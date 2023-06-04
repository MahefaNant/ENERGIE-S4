package frame;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.GridLayout;  
import java.util.Vector;
import inserted.Materiel;
import listener.Info;

public class House extends JPanel{
    private Board board;

    public House (Board board){
        this.board = board;
        initUI();
    }

    private void initUI(){
        setLayout(new GridLayout(3,3));
        setBounds(300, 0, 300, 300);
        board.add(this);

        Border blackline = BorderFactory.createTitledBorder("Maison");
        setBorder(blackline);
        drawList();
    }

    public void drawList(){
        removeAll();
        Vector<Materiel> mats = board.getMats();
        for (Materiel materiel : mats) {
            IconLabel tmp = materiel.getIconLabel();
            addIconLabel(tmp,materiel);
            add(tmp);
        }
        validate();
    }

    public void addIconLabel(IconLabel tmp,Materiel eng){
        tmp.addMouseListener(new Info(eng,board));
        add(tmp);
    }
}
