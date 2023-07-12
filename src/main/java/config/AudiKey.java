package config;

public class AudiKey {

    private static String key;

    public static String get() {
        return key;
    }

    public static void set(String k) {
        key = k;
    }
}
