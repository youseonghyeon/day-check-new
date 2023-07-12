package tester;

import config.ValidationConfig;
import connector.ConnectionManager;
import dto.Node;
import okhttp3.Call;

/**
 * Traffic API 테스트
 */
public class PbTest {

    public static boolean unitTest(Node node) {
        return trafficTest(node);
    }

    /**
     * Traffic 파일 다운로드
     * 검증 방법 : 파일 사이즈, 파일 다운로드 시간, 파일 다운로드 여부
     *
     * @param node
     * @return status
     */
    private static boolean trafficTest(Node node) {
        String fullUrl = node.getUrl() + "/api/traffic/congestion";

        Call call = ConnectionManager.createCall(fullUrl, "GET", null);

        return Tester.test(call, (res -> {
            boolean sizeResult = Tester.sizeValidate(res, ValidationConfig.MIN_PB_SIZE, ValidationConfig.MAX_PB_SIZE);
            boolean durationResult = Tester.dateValidate(res, ValidationConfig.PB_DURATION);
            boolean fileDownResult = Tester.fileDown(res, "./", node);
            return sizeResult & durationResult & fileDownResult;
        }));

    }


}
