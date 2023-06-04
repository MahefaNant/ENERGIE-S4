package frame;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BLog extends JPanel{
    private Board board;
    private JLabel label;

    public BLog (Board board){
        this.board = board;
        initUI();
    }

    private void initUI(){
        setBounds(0, 300, 600, 200);
        board.add(this);

        Border blackline = BorderFactory.createTitledBorder("Log");
        setBorder(blackline);
        label = new JLabel();
        add(label);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    





}
