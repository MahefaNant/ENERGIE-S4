package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import frame.Board;
import reflect.Reflect;

public class Info implements MouseListener {

    private Object obj;
    private Board board;

    public Info(Object obj,Board board){
        this.obj = obj;
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        board.setTextLog(getLog());
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        board.setTextLog(getLog());
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    private String getLog(){
        String tmp = "<h3> Informations:</h3>";
        Reflect ref = new Reflect(obj);
        tmp += ref.getValueOf("html");
        return "<html>"+tmp+"</html>";
    }
    
}
