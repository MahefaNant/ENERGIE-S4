package inserted;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.lang.reflect.Field;

import database.connectivity.PGConnectivity;
import frame.AppChoice;
import frame.IconLabel;
import listener.MouseDraggerApp;
import reflect.Reflect;
import reflect.converter.ConverterString;
import tool.MyTime;

public class Materiel extends Inserted{
 
    private String idMateriel;
    private String idUser;
    private String nom;
    private Double puissance;
    private LocalTime debutHeure;
    private LocalTime finHeure;

    public Materiel() {
        super("idMateriel", "materiel");
    }

    public Materiel(String idMateriel,String idUser, String nom, Double puissance, LocalTime debutHeure,
            LocalTime finHeure) {
        super("idMateriel", "materiel");
        this.idUser = idUser;
        this.idMateriel = idMateriel;
        this.nom = nom;
        this.puissance = puissance;
        this.debutHeure = debutHeure;
        this.finHeure = finHeure;
    }

    public static void main(String[] args) throws SQLException, Exception {
       maxNeededEngDay(getList());
    }

    public static String logMat (Vector<Materiel> mats){
        String tmp = "<h3> Appareils en ce moment </h3>";
        String[] headers = {"nom","puissance","debutHeure","finHeure"};
        tmp += new Table(headers,mats).getHtml();
        return tmp;
    }

    public static void insertAll(Vector<Materiel> mats,String user_id) throws SQLException{
        for (Materiel mat : mats) {
            mat.setIdUser(user_id);
            mat.insert(PGConnectivity.getCon());
        }
    }

    public static Vector<Materiel> listOf(String id_user) throws SQLException, Exception{
        Inserted[] ins = new Materiel("", id_user, "",null, null, null).select(PGConnectivity.getCon());
        Materiel[] tmp = Arrays.copyOf(ins, ins.length, Materiel[].class);
        Vector<Materiel> vect = new Vector<Materiel> (5,10);
        Collections.addAll(vect, tmp);
        return vect;
    }

    public static Materiel of (String nom,String puissance,String debutH,String finH){
        try {
            double dPuissance = new ConverterString(puissance).toDouble();
            LocalTime dh = new ConverterString(debutH).toTime();
            LocalTime df = new ConverterString(finH).toTime();
            return new Materiel("","",nom,dPuissance,dh,df);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ImageIcon loadImage(){
        String path = "/home/aina/projects/Java/energie/src/asset/Materiel.png";
        ImageIcon orgIcon = new ImageIcon(path);
        Image scaled = orgIcon.getImage().getScaledInstance(40,30,Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public IconLabel getIconLabel(){
        return new IconLabel(loadImage(),nom);
    }


    public void doDrawing(AppChoice pan,int x, int y){
        
        IconLabel label =  new IconLabel(loadImage(),"Ajouter");
        label.setBounds(x, y, 50, 50);
        label.addMouseListener(new MouseDraggerApp(pan));
        label.addMouseMotionListener(new MouseDraggerApp(pan));
        label.setVisible(true);
        pan.add(label);
    }

    private static double neededEngIn(Materiel[] mats,LocalTime minHour,LocalTime maxHour){
        double sum = 0;

        for (Materiel materiel : mats) {
            if(minHour.isAfter(materiel.debutHeure) && maxHour.isBefore(materiel.finHeure))
                sum += materiel.puissance;
        } 
        return sum;
    }

    private static double[] neededEngsDay(Materiel[] mats){
        double[] result = new double[12];
        double[] vars = Solaire.getVariations();
        LocalTime minHour = LocalTime.of(06,01);
        LocalTime maxHour = LocalTime.of(06,59);

        System.out.println("Energie besoin en fonction de l'interval de temps\n");
        for (int i = 0; i < result.length; i++) {
            result[i] = neededEngIn(mats,minHour,maxHour) / vars[i];
            System.out.println("Entre "+minHour.toString()+" et "+maxHour.toString()+" "+result[i]);
            minHour = minHour.plusHours(1);
            maxHour = maxHour.plusHours(1);
        }
        return result;
    }

    public static double maxNeededEngDay(Materiel[] mats){
        double[] engs = neededEngsDay(mats);
        double max = 0;

        for (int i = 0; i < engs.length; i++) {
            if(engs[i] > max)
                max = engs[i];
        }
        System.out.println("Maximum energy besoin "+max);
        return max;
    }

    public double getNeededEng (boolean day){
        double coeff = 0;
        LocalTime tmp = debutHeure.plusMinutes(1);
        LocalTime max = LocalTime.of(finHeure.getHour(),finHeure.getMinute(),1);

        while(tmp.isBefore(max)){
            
            if(day && MyTime.isDay(tmp))
                coeff ++; 
            if(!day && MyTime.isNight(tmp))
                coeff ++; 
            tmp = tmp.plusMinutes(1);
        }

        System.out.println("\t"+nom+": "+MyTime.toHour(coeff)+"*"+puissance);
        return MyTime.toHour(coeff)*puissance;
    }

    public static double getSumNeededEng(Materiel[] mats,boolean day){
        double sum = 0;
        String d = (day) ? "jour" : "nuit";

        System.out.println("Somme energie necessaire "+d);
        for (Materiel materiel : mats) {
            sum += materiel.getNeededEng(day);
        }
        return sum;
    }

    public String getHtml(){
        String tmp = "";
        Reflect ref = new Reflect(this);
        Field[] fields = ref.getAllFields();

        for (Field field : fields) {
            tmp += field.getName()+" : "+ref.getValueOf(field.getName())+"<br>";
        }
        return "<html><h3> Vous venez d'ajouter un appareil</h3> <br>"+tmp+"</html>";
    }

    public static Materiel[] getList() throws SQLException, Exception{
        Inserted[] ins = new Materiel().select(PGConnectivity.getCon());
        return Arrays.copyOf(ins,ins.length,Materiel[].class);
    }

    public String getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(String idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPuissance() {
        return puissance;
    }

    public void setPuissance(Double puissance) {
        this.puissance = puissance;
    }

    public LocalTime getDebutHeure() {
        return debutHeure;
    }

    public void setDebutHeure(LocalTime debutHeure) {
        this.debutHeure = debutHeure;
    }

    public LocalTime getFinHeure() {
        return finHeure;
    }

    public void setFinHeure(LocalTime finHeure) {
        this.finHeure = finHeure;
    }
}
