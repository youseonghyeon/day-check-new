package config;

import dto.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeList {

    public static List<Node> nodes = new ArrayList<>();

    public static List<Node> getNodes() {
        return nodes;
    }

    public static void add(Node node) {
        nodes.add(node);
    }
}
