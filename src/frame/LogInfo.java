package frame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import button.Valider;

import java.awt.GridLayout;  

public class LogInfo extends JPanel{

    private JTextField[] fields ;
    private Board board;

    public LogInfo(Board board){
        super();
        this.board = board;
        initUI();
    }

    private void initUI(){
        setLayout(new GridLayout(5,2));
       addLabel();
       add(new Valider(this));
    }

    private void addLabel(){
        String[] label = {"nom","puissance","debut h","fin h"};
        fields = new JTextField[label.length];
        for(int i=0;i<label.length;i++){
            add(new JLabel(label[i]));
            fields[i] = new JTextField(20);
            add(fields[i]);
        }
    }

    public JTextField[] getFields(){
        return fields;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    

    
    
}
