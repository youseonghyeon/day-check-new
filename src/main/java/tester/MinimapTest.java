package tester;

import config.ValidationConfig;
import connector.ConnectionManager;
import dto.Node;
import okhttp3.Call;

import java.util.HashMap;
import java.util.Map;

/**
 * 미니맵 API 테스트
 */
public class MinimapTest {

    private static Map<String, String> minimapHeader = new HashMap<>();

    public static boolean unitTest(Node node) {
        minimapHeader.put("clientRequestId", "klsjdflksdjf");
        boolean minimapListTestResult = minimapListTest(node);
        boolean minimapGetTestResult = minimapGetTest(node);
        return minimapListTestResult && minimapGetTestResult;
    }

    /**
     * 미니맵 리스트 검색 테스트
     * 검증 방법 : keyword가 포함되어 있는지 검증
     *
     * @param node
     * @return status
     */
    private static boolean minimapListTest(Node node) {
        String fullUrl = node.getUrl() + "/minimaps/contries/kr/minimapids?brand=Audi&lan=Kr";

        Call call = ConnectionManager.createCall(fullUrl, "GET", minimapHeader);

        return Tester.test(call, (res -> Tester.keywordValidate(res, ValidationConfig.MINIMAP_LIST)));
    }

    /**
     * 미니맵 .svg파일 다운로드
     * 검증 방법 : 파일 사이즈, 다운로드 여부 + [직접 검증] 파일 생성 시간
     *
     * @param node
     * @return status
     */
    private static boolean minimapGetTest(Node node) {
        String fullUrl = node.getUrl() + "/minimaps/contries/kr/minimapids/m1u0,m100u0?brand=Audi&lan=Kr";

        Call call = ConnectionManager.createCall(fullUrl, "GET", minimapHeader);

        return Tester.test(call, (res -> {
            boolean sizeResult = Tester.sizeValidate(res, ValidationConfig.MIN_SVG_SIZE, ValidationConfig.MAX_SVG_SIZE);
            boolean fileDownResult = Tester.fileDown(res, ValidationConfig.DOWNLOAD_PATH, node);
            return sizeResult & fileDownResult;
        }));
    }
}
