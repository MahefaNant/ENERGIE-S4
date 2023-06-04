package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.Board;
import frame.Gestion;
import inserted.Energie;
import inserted.Materiel;

public class Load implements ActionListener {
    private Gestion gestion;
    private String id_user;

    public Load(Gestion g,String idUser){
        this.gestion = g;
        this.id_user = idUser;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        Board board = gestion.getContent().getBoard();
        try {
            board.setEngs(Energie.listOf(id_user));
            board.setMats(Materiel.listOf(id_user));
            board.getSource().drawList();
            board.getHouse().drawList();
            board.setTextLog("<html> <i>Configuration chargee </i></html>");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
