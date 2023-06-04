package inserted;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.ImageIcon;
import java.awt.Image;

import database.connectivity.PGConnectivity;
import frame.EngChoice;
import frame.IconLabel;
import listener.MouseDraggerEng;
import reflect.Reflect;
import tool.Status;

public class Energie extends Inserted {

    private String idEnergie;
    private Double puissance;
    private String titre;
    private String type;
    private Double prix;

    public Energie(String pk,String tableName){
        super(pk,tableName);
    }

    public Energie() {
        super("idEnergie", "energie");
        // TODO Auto-generated constructor stub
    }

    public Energie(String idEnergie, Double puissance, String titre, String type,
            Double prix) {
        super("idEnergie", "energie");
        this.idEnergie = idEnergie;
        this.puissance = puissance;
        this.titre = titre;
        this.type = type;
        this.prix = prix;
    }

    public static void main(String[] args) throws SQLException, Exception {
        Energie[] list = getList();
    }

    public String getHtml(){
        String tmp = "";
        Reflect ref = new Reflect(this);
        String[] headers = {"titre","puissance","type","prix"};

        for (String header : headers) {
            tmp += ref.getValueOf(header)+"</br>";
        }
        return tmp;
    }

    public static String logEng (Vector<Energie> engs){
        String tmp = "";
        String[] headers = {"titre","puissance","prix"};
        tmp += new Table(headers,engs).getHtml();
        return tmp;
    }

    public static Energie getEnergy(Energie[] engs,Produit pr){
        for (Energie energie : engs) {
            if(energie.getIdEnergie().equals(pr.getIdEnergie()))
                return energie;
        }
        return null;
    }

    public static Vector<Energie> listOf(String id_user) throws SQLException, Exception{
        Produit[] tmp = Produit.getListOf(id_user);
        Energie[] list = getList();
        Vector<Energie> res = new Vector<>(5, 10);
        for (Produit pr : tmp) {
            res.add(getEnergy(list,pr));
        }
        return res;
    }

    public static Energie[] getList() throws SQLException, Exception{
        Inserted[] ins = new Energie().select(PGConnectivity.getCon()); 
        return Arrays.copyOf(ins,ins.length,Energie[].class);
    } 

    public static String getId(Energie eng,Energie[] list){
        for (Energie energie : list) {
            if(eng.getTitre().equals(energie.getTitre()))
                return energie.getIdEnergie();
        }
        return "";
    }

    private static void addInto(Energie[] engs,Vector<Energie> res){
        for (Energie eng : engs) {
            res.add(eng);
        }
    }

    public Status isOverDimension(Energie[] engs,Materiel[] mats){
        double needed = (getClass() == Batterie.class) ? Materiel.getSumNeededEng(mats, false) : Materiel.maxNeededEngDay(mats);
        for (Energie eng : engs) {
            needed -= (eng.getClass() == getClass()) ? eng.getPuissance() : 0;
        }
        return new Status(((getClass() == Batterie.class) ? "Nuit":"Jour"),needed);
    }

    public static Status[] getStatus(Vector<Energie> engs,Vector<Materiel> mats){
        Status[] res = new Status[2];
        Energie[] tmpE = engs.toArray(new Energie[engs.size()]);
        Materiel[] tmpM = mats.toArray(new Materiel[mats.size()]);
        res[0] = new Solaire().isOverDimension(tmpE,tmpM);
        res[1] = new Batterie().isOverDimension(tmpE,tmpM);
        return res;
    }

    public static Vector<Energie> getListEng(Vector<Materiel> mats){
        Materiel[] tmp = mats.toArray(new Materiel[mats.size()]);
        Vector<Energie> res = new Vector<>(5,10);
        try {
            Batterie[] batts = Batterie.getAppropriateBatts(tmp);
            Solaire[] sols = Solaire.getAppropriateSols(tmp);
            addInto(batts,res);
            addInto(sols,res);
            return res;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    protected static Energie getAppropriateEng(Energie[] engs, double sum) {
        Energie rs = engs[0];
        double diff = sum - rs.getPuissanceWithSeuil();
        double tmp = 0;

        for (int i = 1; i < engs.length; i++) {
            tmp = sum - engs[i].getPuissanceWithSeuil();
            if ((diff < tmp && tmp <= 0) || (diff > tmp && tmp >= 0)) {
                diff = tmp;
                rs = engs[i];
            }
        }
        System.out.println("\t" + rs.getType() + " " + rs.getTitre() + " puissance: " + rs.getPuissance());
        return rs;
    }

    protected static Energie[] getAppropriateEngs(Materiel[] mats, Energie[] engs) throws SQLException, Exception {

        double sum = (engs[0].getClass() == Batterie.class) ? Materiel.getSumNeededEng(mats, false) : Materiel.maxNeededEngDay(mats);
        Vector<Energie> rs = new Vector<>(5, 10);
        Energie tmp = null;
        System.out.println(engs[0].getType()+" neccessaire pour la configuration:");

        while (sum > 0) {
            tmp = getAppropriateEng(engs, sum);
            sum -= tmp.getPuissanceWithSeuil();
            rs.add(tmp);
        }
        return rs.toArray(new Energie[rs.size()]);
    }

    public ImageIcon loadImage(){
        String path = "/home/aina/projects/Java/energie/src/asset/"+getType()+".png";
        ImageIcon orgIcon = new ImageIcon(path);
        Image scaled = orgIcon.getImage().getScaledInstance(40,25,Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public IconLabel getIconLabel(){
        return new IconLabel(loadImage(),getPuissanceWithoutSeuil()+" W");
    }

    public void doDrawing(EngChoice pan,int x, int y){
        IconLabel label = getIconLabel();
        label.setBounds(x, y, 50, 50);
        MouseDraggerEng md = new MouseDraggerEng(this,pan,y);
        label.addMouseMotionListener(md);
        label.addMouseListener(md);
        pan.add(label);
    }

    public Double getPuissanceWithSeuil(){
        return getPuissance();
    }

    public Double getPuissanceWithoutSeuil(){
        return getPuissance();
    }

    public String getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(String idEnergie) {
        this.idEnergie = idEnergie;
    }

    public Double getPuissance() {
        return puissance;
    }

    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    
}
