package win;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.plaf.ColorUIResource;

import win.Tapis;
import win.Window;

public class Mix {
    private int blockSize = 30;
    Window win;
    Tapis tapis;
    JLabel [] lab = new JLabel[1];

    public Mix() {
        int w = 50*blockSize;
        int h = 30*blockSize;
        tapis = new Tapis(0, 0, w, h, new ColorUIResource(Color.decode("#111")));
        lab[0] = tapis;
        win = new Window("Energie", w, h, true, lab);
    }
    
}
