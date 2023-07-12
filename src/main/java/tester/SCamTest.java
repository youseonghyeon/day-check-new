package tester;

import config.ValidationConfig;
import connector.ConnectionManager;
import dto.Node;
import okhttp3.Call;

/**
 * Speed Camera API 테스트
 */
public class SCamTest {

    public static boolean unitTest(Node node) {
        return speedCameraListTest(node);
    }

    /**
     * Speed Camera List 검색 테스트
     * 검증 방법 : 파일 사이즈, 파일 다운로드 여부
     *
     * @param node
     * @return status
     */
    private static boolean speedCameraListTest(Node node) {
        String fullUrl = node.getUrl() + "/api/safety/camera";

        Call call = ConnectionManager.createCall(fullUrl, "GET", null);

        return Tester.test(call, (res -> {
            boolean sizeResult = Tester.sizeValidate(res, ValidationConfig.MIN_SCAM_SIZE, ValidationConfig.MAX_SCAM_SIZE);
            boolean fileDownResult = Tester.fileDown(res, ValidationConfig.DOWNLOAD_PATH, node);
            return sizeResult & fileDownResult;
        }));
    }


}
