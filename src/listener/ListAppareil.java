package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.Board;
import frame.Gestion;
import inserted.Energie;
import inserted.Materiel;

public class ListAppareil implements ActionListener{

    private String type;
    private Gestion gestion;

    

    public ListAppareil(String type, Gestion gestion) {
        this.type = type;
        this.gestion = gestion;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Board board = gestion.getContent().getBoard();
        if(type.equals("Materiel")){
            board.setTextLog("<html>"+Materiel.logMat(board.getMats())+"</html>");
        }else {
            board.setTextLog("<html><h3> source en ce moment </h3>"+Energie.logEng(board.getEngs())+"</html>");
        }
    }
    
}
