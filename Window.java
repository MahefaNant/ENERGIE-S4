package win;

import javax.swing.*;

import java.awt.*;

public class Window extends JFrame{
    Object[] obj;
    JPanel pan;
    public Window(String title,int w,int h,boolean close,Object[] obj) {
        this.obj = obj;
        pan = panel(this);
        if(this.obj!=null) for(int i=0;i<obj.length;i++) {
            pan.add((Component) this.obj[i]);
        }
        this.setSize(w, h);
		this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        if(close==true) this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    JPanel panel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        //panel.setBackground(Color.decode("#111"));
        frame.add(panel);
        return panel;
    }
    
}