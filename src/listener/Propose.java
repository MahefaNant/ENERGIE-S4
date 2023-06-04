package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import frame.Board;
import frame.Gestion;
import inserted.Energie;
import inserted.Materiel;

public class Propose implements ActionListener{

    private Gestion gestion;
    
    public Propose(Gestion g){
        System.out.println("propose");
        this.gestion = g;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("propose");
        Board board = gestion.getContent().getBoard();
        
        Vector<Materiel> lists = board.getMats();
        Vector<Energie> tmp = Energie.getListEng(lists);
        board.setEngs(tmp);
        board.getSource().drawList();
        board.setTextLog("<html> <h3 style='color:red;' > Configuration suffisant </h3>"+Energie.logEng(tmp)+"</html>");
    }
    
}
