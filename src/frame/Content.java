package frame;

import javax.swing.JPanel;

public class Content extends JPanel {

    private EngChoice batts;
    private EngChoice sols;
    private Board board;
    private AppChoice choice;
    private Gestion gestion;

    public Content(Gestion g) {
        this.gestion = g;
        addComponents();
    }

    private void addComponents() {
        setLayout(null);

        batts = new EngChoice("Batterie", this);
        sols = new EngChoice("Solaire", this);
        choice = new AppChoice(this);
        board = new Board(this);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Gestion getGestion() {
        return gestion;
    }

    public EngChoice getBatts() {
        return batts;
    }

    public void setBatts(EngChoice batts) {
        this.batts = batts;
    }

    public AppChoice getChoice() {
        return choice;
    }

    public void setChoice(AppChoice choice) {
        this.choice = choice;
    }

    public EngChoice getSols() {
        return sols;
    }

    public void setSols(EngChoice sols) {
        this.sols = sols;
    }

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }
}
