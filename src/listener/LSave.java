package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

import frame.Gestion;
import frame.LogNom;

public class LSave implements ActionListener{

    private Gestion gestion;
    private LogNom dialog;
    
    public LSave (Gestion g){
        this.gestion = g;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        dialog = new LogNom(gestion);

        JDialog jd = new JDialog();
        jd.setSize(300,125);
        jd.add(dialog);
        jd.setVisible(true);
        jd.setLocationRelativeTo(null);   
    }
    
}
