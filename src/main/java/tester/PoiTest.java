package tester;

import config.ValidationConfig;
import connector.ConnectionManager;
import dto.Node;
import okhttp3.Call;
import okhttp3.Response;
import utils.ResultPrinter;
import validation.Validator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * POI API 테스트
 */
public class PoiTest {

    public static boolean unitTest(Node node) {
        boolean poiListResult = poiListSearchTest(node);
        boolean poiDopiReuslt = poiDopiSearchTest(node);
        boolean poiDetailResult = poiDetailSearchTest(node);
        return poiListResult && poiDopiReuslt && poiDetailResult;
    }

    /**
     * POI List 검색 테스트 (일반 검색)
     * 검증 방법 : keyword가 포함되어 있는지 검증
     *
     * @param node
     * @return status
     */
    private static boolean poiListSearchTest(Node node) {
        String fullUrl = node.getUrl() + "/api/pois?q=seoul station&lat=37.443&lon=126.801";

        Call call = ConnectionManager.createCall(fullUrl, "GET", null);
        return Tester.test(call, (res -> Tester.keywordValidate(res, ValidationConfig.POI_DOPI_SEARCH)));
    }

    /**
     * POI List 검색 테스트 (일반 검색)
     * 검증 방법 : keyword가 포함되어 있는지 검증
     *
     * @param node
     * @return status
     */
    private static boolean poiListCategorySearchTest(Node node) {
        String fullUrl = node.getUrl() + "/api/pois?q=seoul station&lat=37.443&lon=126.801&fullmatch=1";

        Call call = ConnectionManager.createCall(fullUrl, "GET", null);

        return Tester.test(call, (res -> Tester.keywordValidate(res, ValidationConfig.POI_DOPI_SEARCH)));
    }

    /**
     * POI DOPI 검색 테스트
     * 검증 방법 : keyword가 포함되어 있는지 검증
     *
     * @param node
     * @return status
     */
    private static boolean poiDopiSearchTest(Node node) {
        String fullUrl = node.getUrl() + "/api/pois";

        Call call = ConnectionManager.createCall(fullUrl, "POST", null);

        return Tester.test(call, (res -> Tester.keywordValidate(res, ValidationConfig.POI_DOPI_SEARCH)));
    }

    /**
     * POI Detail 검색 테스트
     * 검증 방법 : keyword가 포함되어 있는지 검증
     *
     * @param node
     * @return status
     */
    private static boolean poiDetailSearchTest(Node node) {
        String fullUrl = node.getUrl() + "/api/pois/9258";

        Call call = ConnectionManager.createCall(fullUrl, "GET", null);

        return Tester.test(call, (res -> Tester.keywordValidate(res, ValidationConfig.POI_DETAIL_SEARCH)));
    }


}
