package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.Board;
import frame.Gestion;
import inserted.Energie;
import tool.Status;

public class LStatus implements ActionListener{

    private Gestion gestion;
    
    public LStatus(Gestion g){
        System.out.println("propose");
        this.gestion = g;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("propose");
        Board board = gestion.getContent().getBoard();
        
        Status[] status = Energie.getStatus(board.getEngs(), board.getMats());
        String tmp = "<html> <h4> Status </h4>" + status[0].toString()+" W<br>"+status[1].toString() + " W<html>";
        board.setTextLog(tmp);
        // board.getLog().validate();
        
    }
    
}
