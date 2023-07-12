package dto;

import com.sun.jndi.toolkit.url.UrlUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class Node {

    private final String domain;

    private final String host;

    private final int port;

    public Node(String domain, String host, int port) {
        this.domain = domain;
        this.host = host;
        this.port = port;
    }

    public Node(String node) {
        try {
            URL url = new URL(node);
            this.domain = url.getProtocol();
            this.host = url.getHost();
            this.port = url.getPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getUrl() {
        return domain + host + ":" + port;
    }

    public String getDomain() {
        return domain;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
