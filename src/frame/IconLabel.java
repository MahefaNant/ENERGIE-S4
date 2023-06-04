package frame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class IconLabel extends JLabel{
    private ImageIcon icon;
    private String text;

    public IconLabel(ImageIcon icon ,String text){
        this.icon = icon;
        this.text = text;
        initUI();
    }

    private void initUI(){
        setLayout(null);
        setSize(50, 50);

        JLabel ic = new JLabel(icon);
        ic.setBounds(0, 0, 50, 30);
        add(ic);

        JLabel msg = new JLabel(text);
        msg.setBounds(0,30,50,20);
        add(msg);
    }
}
