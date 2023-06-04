package frame;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import inserted.Energie;
import listener.Info;

import java.awt.GridLayout;
import java.util.Vector;

public class BSource extends JPanel{
    private Board board;

    public BSource (Board board){
        this.board = board;
        initUI();
    }

    private void initUI(){
        setLayout(new GridLayout(3,3));
        setBounds(0, 0, 300, 300);
        board.add(this);

        Border blackline = BorderFactory.createTitledBorder("Sources");
        setBorder(blackline);
        drawList();
    }

    public void drawList(){
        removeAll();
        Vector<Energie> engs = board.getEngs();
        for (Energie eng : engs) {
            IconLabel tmp = eng.getIconLabel();
            addIconLabel(tmp,eng);
            add(tmp);
        }
        validate();
    }

    public void addIconLabel(IconLabel tmp,Energie eng){
        tmp.addMouseListener(new Info(eng,board));
        add(tmp);
    }
}
