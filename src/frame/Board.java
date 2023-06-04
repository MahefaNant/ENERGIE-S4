package frame;

import javax.swing.JPanel;

import inserted.Energie;
import inserted.Materiel;

import java.util.Vector;

public class Board extends JPanel{

    private Vector<Materiel> mats = new Vector<> (5,10) ;
    private Vector<Energie> engs = new Vector<> (5,10);
    private House house;
    private BSource source;
    private BLog log;

    
    public Board(Content gestion){
        initUI(gestion);
    }

    private void initUI(Content g){
        setLayout(null);
        setBounds(200, 0, 600, 500);
        g.add(this);
        house = new House(this);
        source = new BSource(this);
        log = new BLog(this);
    }

    public void setTextLog(String s){
        log.getLabel().setText(s);
    }

    public void addMateriel(Materiel mat){
        mats.add(mat);
        house.addIconLabel(mat.getIconLabel(),mat);
        house.validate();
    }

    public void addEnergy(Energie eng){
        engs.add(eng);
        source.addIconLabel(eng.getIconLabel(),eng);
        source.validate();
    }

    public Vector<Energie> getEngs() {
        return engs;
    }

    public void setEngs(Vector<Energie> engs) {
        this.engs = engs;
    }

    public Vector<Materiel> getMats() {
        return mats;
    }

    public void setMats(Vector<Materiel> mats) {
        this.mats = mats;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public BSource getSource() {
        return source;
    }

    public void setSource(BSource source) {
        this.source = source;
    }

    public BLog getLog() {
        return log;
    }

    public void setLog(BLog log) {
        this.log = log;
    }
    
}
