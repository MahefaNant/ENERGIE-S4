package tool;

import java.time.LocalTime;

public class MyTime {

    private static boolean  isBtw(LocalTime time,LocalTime min,LocalTime max){
        return time.isAfter(min) && time.isBefore(max);
    }

    //Need to plus 1 second bcs if it is at 0:0 it won t be true
    public static boolean isNight(LocalTime time){  //18:00 -> 6:00:01
        return isBtw(time,LocalTime.of(18,0),LocalTime.of(23,59,1)) || isBtw(time.plusSeconds(1),LocalTime.of(0,0),LocalTime.of(6, 0, 2));
    }
    
    public static boolean isDay(LocalTime time){ //6:00 -> 18:00:01
        return isBtw(time,LocalTime.of(6,0),LocalTime.of(18,0,1));
    }

    public static double toHour (double min){
        return min /60;
    }

    public static void main(String[] args) {
        System.out.println(isNight(LocalTime.of(13,0)));
    }
}
