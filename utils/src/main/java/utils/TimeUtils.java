package utils;

public class TimeUtils {

    public static final int HOUR = 1000 * 60 * 60;
    public static final int MINUTE = 1000 * 60;
    public static final int SECOND = 1000;

    /**
     * A utility method to format time
     * @param millis
     * @return
     */
    public static String formatTimePeriod(long millis){
        String time = "";
        if(millis > HOUR) {
            time +=(millis/HOUR)+" h, ";
            millis = millis % HOUR;
        }
        if(millis > MINUTE) {
            time +=(millis/MINUTE)+" m, ";
            millis = millis % MINUTE;
        }
        if(millis > SECOND) {
            time +=(millis/SECOND)+ " s, ";
            millis = millis % SECOND;
        }
        time+= millis+" ms";
        return time;
    }
}
