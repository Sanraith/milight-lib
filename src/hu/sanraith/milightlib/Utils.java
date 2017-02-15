package hu.sanraith.milightlib;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
    private static final String IP_PATTERN = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static boolean sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidIp(String ip) {
        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
