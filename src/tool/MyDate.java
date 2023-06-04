package tool;

import java.time.Duration;
import java.time.LocalDate;

public class MyDate {
    public static long diffDate(LocalDate start, LocalDate end) {
        // LocalDate d1 = LocalDate.now();
        // LocalDate d2 = LocalDate.parse("2021-01-12",
        // DateTimeFormatter.ISO_LOCAL_DATE);
        // between (startinclusive,endexclusive)
        Duration diff = Duration.between(start.atStartOfDay(), end.atStartOfDay());
        return diff.toDays();
    }

    public static LocalDate dateOfFirst(int valueOfday, int year) {
        LocalDate res = LocalDate.of(year, 1, 1);
        while (true) {
            if (res.getDayOfWeek().getValue() == valueOfday) {
                return res;
            }
            System.out.println(res.toString() + " " + res.getDayOfWeek().getValue());
            res = res.plusDays(1);
        }
    }

    public static void main(String[] args) {
       int[] tmp = {2,0,-9,5,7,8};
        int min = tmp[0];

        for (int i : tmp) {
            if(i < min)
                min = i;
        }
        System.out.println("min est "+min);
    }
}
