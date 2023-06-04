package listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import database.connectivity.PGConnectivity;
import frame.Board;
import frame.LogNom;
import frame.Menu;
import inserted.Inserted;
import inserted.Materiel;
import inserted.Produit;
import inserted.User;

public class LEnregistre implements ActionListener {

    private LogNom log;
    
    public LEnregistre (LogNom log){
        this.log = log;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println("tonga"); 
        User user = new User("",log.getNom().getText());
        Board board = getBoard();
        try {
            Connection con = PGConnectivity.getCon();
            user.insert(con);
            Inserted[] ins = user.select(con);
            User tmp = (User)ins[0];
            Materiel.insertAll(board.getMats(), tmp.getIdUser());
            Produit.insertAll(board.getEngs(), tmp.getIdUser());
            board.setTextLog("<html> <i>Configuration sauvegarde </i></html>");
            getMenu().updateLoad();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Board getBoard(){
        return log.getGestion().getContent().getBoard();
    }

    private Menu getMenu(){
        return (Menu) log.getGestion().getJMenuBar();
    }
    
}
