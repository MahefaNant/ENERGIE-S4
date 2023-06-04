package frame;

import javax.swing.JFrame;

public class Gestion extends JFrame{
    
    Content content;

    public Gestion (){
        initUI();
    }

    public static void main(String[] args) {
        new Gestion();
    }

    private void addComponents(){
        setJMenuBar(new Menu(this));
        content = new Content(this);
        setContentPane(content);
        setGlassPane(new GPane(this));
    }

    private void initUI(){
        addComponents();
        setTitle("Gestion");
        setSize(910, 565);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }    
   
}
