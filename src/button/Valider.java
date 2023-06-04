package button;

import javax.swing.JButton;

import frame.LogInfo;
import listener.LValider;

public class Valider extends JButton {

    public Valider(LogInfo log){
        super("Valider");
        addActionListener(new LValider(log));
    }


}
