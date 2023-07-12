package utils;

import dto.Node;

public class ResultPrinter {

    public static void printSuccess(Node node) {
        String server = "[" + node.getHost() + "]";
        System.out.printf("-14%s 테스트 성공", server);
    }

    public static void printFail(Node node) {
        String server = "[" + node.getHost() + "]";
        System.out.printf("-14%s 테스트 성공", server);
    }

    public static void printFail(Node node, String msg) {
        String server = "[" + node.getHost() + "]";
        System.out.printf("-14%s 테스트 성공", server);
    }

    public static void print() {
        System.out.println("print test");
    }
}
