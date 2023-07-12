package tester;

import dto.Node;
import okhttp3.Call;
import okhttp3.Response;
import validation.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class Tester {

    protected static boolean test(Call call, Validator validator) {
        try (Response response = call.execute()) {
            if (!response.isSuccessful()) {
                return false;
            }
            return validator.proceed(response);
        } catch (IOException e) {
            return false;
        }
    }

    public static String getBody(Response res) {
        try {
            return res.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean keywordValidate(Response res, List<String> keywords) {
        String body = getBody(res);
        for (String keyword : keywords) {
            if (!body.contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    public static boolean sizeValidate(Response res, long minSize, long maxSize) {
        long size = 0;
        try {
            size = res.body().bytes().length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return size >= minSize && size <= maxSize;
    }

    public static boolean dateValidate(Response res, int duration) {
        return false;
    }

    public static boolean fileDown(Response res, String path, Node node) {
        LocalDateTime now = LocalDateTime.now();
        int min = now.getMinute();
        int hour = now.getHour();

        String fileName = res.header("Content-Disposition");
        if (fileName == null || fileName.equals("")) {
            fileName = "test" + hour + min + ".json";
        }
        try (PrintWriter pw = new PrintWriter(path + fileName)) {
            pw.print(res.body().string());
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            return false;
        } catch (IOException e) {
            System.out.println("파일을 쓸 수 없습니다.");
            return false;
        }
    }

}
