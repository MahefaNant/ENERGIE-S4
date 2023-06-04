package win;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormA extends JFrame{
    public JTextField[] input;
    public JButton valider;
    public JButton annuler;
    public FormA() {
        this.setSize(500, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBounds(0, 0, getWidth() ,getHeight());
        pan.setBackground(Color.darkGray);
        addLab(pan);
        addInput(pan);
        addBut(pan);
        this.add(pan);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void addLab(JPanel pan) {
        JLabel[] lab = new JLabel[4];
        int [] sep = {0,0};
        lab[0] = new JLabel("Add Appareil",JLabel.CENTER);
        lab[1] = new JLabel("Nom: ",JLabel.CENTER);
        lab[2] = new JLabel("Puissance: ",JLabel.CENTER);
        lab[3] = new JLabel("Time: ",JLabel.CENTER);
        for(int i=0;i<lab.length;i++) {
            lab[i].setBounds(5, 5 + sep[0], 120, 30);
            lab[i].setOpaque(true);
            lab[i].setFont(new java.awt.Font("Dialog", 0, 18));
            pan.add(lab[i]);
            sep[0]+=30*3;
        }
        //jLabel2.setFont(new java.awt.Font("Dialog", 0, 14));
    }

    public void addInput(JPanel pan) {
        int sep =0;
        input = new JTextField[4];
        input[0] = new JTextField();
        input[1] = new JTextField();
        input[2] = new JTextField();
        input[3] = new JTextField();

        input[0].setBounds(30*6,90 , 120*2, 40);
        input[1].setBounds(30*6,90*2 , 120*2, 40);
        input[2].setBounds(30*6,90*3 , 80, 40);
        input[3].setBounds(30*12,90*3 , 80, 40);
        for(int i =0;i<input.length;i++) {
            pan.add(input[i]);
        }
    }

    public void addBut(JPanel pan) {
        valider = new JButton("Valider");
        valider.setBounds(30, 90*4, 100, 40);
        annuler = new JButton("annuler");
        annuler.setBounds(120, 90*4, 100, 40);
        annuler.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        pan.add(valider);
        pan.add(annuler);
    }

}
