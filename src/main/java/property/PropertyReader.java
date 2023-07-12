package property;

import config.AudiKey;
import config.NodeList;
import config.ValidationConfig;
import dto.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertyReader {

    public void read() {
        try (FileReader fr = new FileReader("application.properties")) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String str;
                while ((str = br.readLine()) != null) {
                    Map<String, String> map = readLine(str);
                    if (map != null) {
                        map.forEach(this::applyProperty);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void applyProperty(String key, String value) {
        switch (key) {
            case "authorization":
                AudiKey.set(value);
                break;
            case "server.node":
                NodeList.add(new Node(value));
                break;
            case "download.path":
                ValidationConfig.DOWNLOAD_PATH = value.endsWith("/") ? value : value + "/";
                break;
            case "validation.min-pb-size":
                ValidationConfig.MIN_PB_SIZE = Integer.parseInt(value);
                break;
            case "validation.max-pb-size":
                ValidationConfig.MAX_PB_SIZE = Integer.parseInt(value);
                break;
            case "validation.min-svg-size":
                ValidationConfig.MIN_SVG_SIZE = Integer.parseInt(value);
                break;
            case "validation.max-svg-size":
                ValidationConfig.MAX_SVG_SIZE = Integer.parseInt(value);
                break;
            case "validation.pb-duration":
                ValidationConfig.PB_DURATION = Integer.parseInt(value);
                break;
            case "validation.poi-list-search":
                ValidationConfig.POI_LIST_SEARCH.add(value);
                break;
            case "validation.poi-dopi-search":
                ValidationConfig.POI_DOPI_SEARCH.add(value);
                break;
            case "validation.poi-detail-search":
                ValidationConfig.POI_DETAIL_SEARCH.add(value);
                break;
            case "validation.speed-cam":
                ValidationConfig.SPEED_CAM.add(value);
                break;
            case "validation.minimap-list":
                ValidationConfig.MINIMAP_LIST.add(value);
                break;
        }
    }

    private Map<String, String> readLine(String line) {
        line = line.contains("#") ? line.split("#")[0] : line;

        line = line.trim();

        if (!line.contains("=")) {
            return null;
        }

        String[] split = line.split("=");
        HashMap<String, String> map = new HashMap<>();
        if (split.length != 2 || split[0].trim().equals("") || split[1].trim().equals("")) {
            return null;
        }
        map.put(split[0], split[1]);

        return map;
    }
}
