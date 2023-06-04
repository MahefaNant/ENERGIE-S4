package frame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import inserted.User;
import listener.LSave;
import listener.LStatus;
import listener.ListAppareil;
import listener.Load;
import listener.Propose;

public class Menu extends JMenuBar{

    private Gestion gestion;
    private JMenu load;

    public Menu(Gestion g){
        gestion = g;
        initUI();
    }

    private void initUI(){
        JMenu newButton = new JMenu("New");
        newButton.addActionListener(null);
        add(newButton);

        JMenu save = new JMenu("Save");
        JMenuItem saveIt = new JMenuItem("save");
        saveIt.addActionListener(new LSave(gestion));
        save.add(saveIt);
        add(save);

        load = new JMenu("Load");
        addLoadOption();
        add(load);

        JMenu propose = new JMenu("Tools");
        JMenuItem sug = new JMenuItem("Suggestion");
        sug.addActionListener(new Propose(gestion));
        propose.add(sug);
        JMenuItem status = new JMenuItem("Status");
        status.addActionListener(new LStatus(gestion));
        propose.add(status);
        add(propose);
        JMenuItem le = new JMenuItem("L. energie");
        le.addActionListener(new ListAppareil("Energie", gestion));
        propose.add(le);
        add(propose);
        JMenuItem la = new JMenuItem("L. materiel");
        la.addActionListener(new ListAppareil("Materiel", gestion));
        propose.add(la);
        add(propose);
    }

    private void addLoadOption(){
        try {
            User[] users = User.getList();
            for (User user : users) {
                JMenuItem tmp = new JMenuItem(user.getNom());
                tmp.addActionListener(new Load(gestion,user.getIdUser()));
                load.add(tmp);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateLoad(){
        load.removeAll();
        addLoadOption();
        repaint();
    }
    
}
