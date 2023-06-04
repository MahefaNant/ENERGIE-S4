package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import frame.Board;
import frame.LogInfo;
import inserted.Materiel;

public class LValider implements ActionListener{

    private LogInfo log;

    public LValider (LogInfo log){
        this.log = log;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JTextField[] fields = log.getFields();
        Board board = log.getBoard();
        Materiel tmp = Materiel.of(fields[0].getText(),fields[1].getText(),fields[2].getText(),fields[3].getText());
        board.addMateriel(tmp);        
        board.setTextLog(tmp.getHtml());
        System.out.println(tmp.getHtml());
        System.out.println("Ajout nouvel appareil dans la liste \n");
    }
    
}
