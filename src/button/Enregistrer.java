package button;

import javax.swing.JButton;

import frame.LogNom;
import listener.LEnregistre;

public class Enregistrer extends JButton {

    public Enregistrer(LogNom log){
        super("Enregistrer");
        addActionListener(new LEnregistre(log));
    }


}