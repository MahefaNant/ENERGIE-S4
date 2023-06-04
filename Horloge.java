package time;

import java.time.*;

public class Horloge {

    private int I,F;

    public Horloge(int I,int F) {
        this.I = I;
        this.F =F;
    }

    public int getDurer() {
        LocalTime locI = loc(getI(), 0);
        LocalTime locF = loc(getF(), 0);
        return locF.minusHours(locI.getHour()).getHour();
    }

    public int getDurer(Horloge energ) {
        LocalTime ElocI = energ.loc(energ.getI(), 0);
        LocalTime ElocF = energ.loc(energ.getF(), 0);
        LocalTime locI = loc(getI(), 0);
        LocalTime locF = loc(getF(), 0);

        if(locI.isAfter(ElocI) && locF.isAfter(ElocF)) return 0;
        else if(locI.isBefore(ElocI) && locF.isBefore(ElocF)) return 0;
        if((locI.isAfter(ElocI) || locI.equals(ElocI)) && 
            (locF.isBefore(ElocF) || locF.equals(ElocF))) return getDurer();
        
        else if((locI.isAfter(ElocI) || locI.equals(ElocI)) &&
                (locF.isAfter(ElocF)))  return ElocF.minusHours(locI.getHour()).getHour();
        
        else if(locI.isBefore(ElocI) &&
                locF.isAfter(ElocI) && (locF.isBefore(ElocF) || locF.equals(ElocF)))  return locF.minusHours(ElocI.getHour()).getHour();

        else if(locI.isBefore(ElocI) && 
                locF.isAfter(ElocF))  return ElocF.minusHours(ElocI.getHour()).getHour();

        else return 0;
    }

    public boolean isEqual(Horloge time) {
        if(this.getI()!=time.getI() && this.getF() != time.getF())
            return false;
        return true;
    }


    public LocalTime loc(int h,int m) {
        LocalTime loc = LocalTime.of(h, m);
        return loc;
    }
    public int getI() {
        return this.I;
    }
    public int getF() {
        return this.F;
    }

}