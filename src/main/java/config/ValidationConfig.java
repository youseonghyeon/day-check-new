package config;

import java.util.ArrayList;
import java.util.List;

public class ValidationConfig {

    public static List<String> POI_LIST_SEARCH = new ArrayList<>();
    public static List<String> POI_DOPI_SEARCH = new ArrayList<>();
    public static List<String> POI_DETAIL_SEARCH = new ArrayList<>();
    public static List<String> SPEED_CAM = new ArrayList<>();
    public static List<String> MINIMAP_LIST = new ArrayList<>();


    public static long MIN_PB_SIZE = 5000000;
    public static long MAX_PB_SIZE = 10000000;

    public static long MIN_SVG_SIZE = 3000;
    public static long MAX_SVG_SIZE = 6000;

    public static long MIN_SCAM_SIZE = 1000000;
    public static long MAX_SCAM_SIZE = 2000000;

    public static int PB_DURATION = 4;

    public static String DOWNLOAD_PATH = "";

}
