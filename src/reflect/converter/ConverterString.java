package reflect.converter;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConverterString {

    String str;
    Class<?>[] possibleClasses = { Integer.class, Double.class, String.class, LocalDate.class, LocalTime.class};
    String[] nameFunction = { "toInt", "toDouble", "toString", "toDate", "toTime"};

    public ConverterString(String str) {
        this.str = str;
    }

    public int toInt() throws Exception {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("must be a number");
        }
    }

    public double toDouble() throws Exception {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("must be a number");
        }
    }

    public int toPositiveInt() throws Exception {
        int nbr = toInt();
        if (nbr < 0)
            throw new Exception("must be a positive value");
        return nbr;
    }

    public String toString() {
        return (str == null) ? "" : str;
    }

    public Object transformInto(Class<?> classToTransform) throws Exception {
        for (int i = 0; i < possibleClasses.length; i++) {
            if (classToTransform == possibleClasses[i]) {
                Method method = getClass().getDeclaredMethod(nameFunction[i]);
                return method.invoke(this);
            }
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public LocalDate toDate() throws Exception {
        String[] DateStrs = str.split(" "); // here the format of the String date is 2021
        String[] strs = DateStrs[0].split("-");

        return LocalDate.of(new ConverterString(strs[0]).toInt(), new ConverterString(strs[1]).toInt(),
                new ConverterString(strs[2]).toInt());
    }

    public LocalTime toTime() throws Exception{
        String[] parts = str.split(":");
        return LocalTime.of(new ConverterString(parts[0]).toInt(), new ConverterString(parts[1]).toInt());

    }

}
