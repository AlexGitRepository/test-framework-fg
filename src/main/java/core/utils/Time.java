package core.utils;

public class Time {

    public static String timeStamp() {
        return 	new java.text.SimpleDateFormat("yyyyMMdd_HHmm").format(java.util.Calendar.getInstance ().getTime());
    }

    public static String timeStampDetailed() {
        return 	new java.text.SimpleDateFormat("yyyyMMdd-HHmmssSSS").format(java.util.Calendar.getInstance ().getTime());
    }

}
