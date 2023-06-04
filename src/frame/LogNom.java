package frame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;  

import button.Enregistrer;

public class LogNom extends JPanel{
    private JTextField nom;
    private Gestion gestion;

    public LogNom (Gestion gestion){
        super();
        this.gestion = gestion;
        initUI();
    }

    private void initUI(){
        setLayout(new GridLayout(2,2));
        addLabel();
        add(new Enregistrer(this));
    }

    private void addLabel(){
        add(new JLabel("nom"));
        nom = new JTextField(20);
        add(nom);
    }

    public JTextField getNom() {
        return nom;
    }

    public void setNom(JTextField nom) {
        this.nom = nom;
    }

    public Gestion getGestion(){
        return gestion;
    }
}
