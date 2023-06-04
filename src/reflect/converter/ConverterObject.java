package reflect.converter;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConverterObject {

    private Object o;
    private Class<?>[] objectType = { LocalDate.class, LocalTime.class};

    public ConverterObject(Object o) {
        this.o = o;
    }

    private String stringValue(int index) {
        switch (index) {
            case 0:
                LocalDate lc = (LocalDate) o;
                return lc.getYear() + "-" + lc.getMonthValue() + "-" + lc.getDayOfMonth();
            case 1:
                LocalTime lt = (LocalTime) o;
                return lt.getHour()+":"+lt.getMinute();
            default:
                String format = (o.getClass() == Double.class) ? "%.2f" : "%s";
                Object[] arg = { o };
                return (o.getClass() == Double.class) ? String.format(format, arg).replace(",", ".")
                        : String.format(format, arg);
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (o == null) {
            return "";
        }
        for (int i = 0; i < objectType.length; i++) {
            if (o.getClass() == objectType[i]) {
                return stringValue(i);
            }
        }
        return stringValue(-1);// par defaut no alefa
    }
}
