package connector;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.Map;

public class ConnectionManager {

    public static Call createCall(String fullUrl, String method, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
                .url(fullUrl)
                .get();

        if (header != null) {
            header.forEach(builder::addHeader);
        }
        Request request = builder.build();
        return client.newCall(request);
    }
}
