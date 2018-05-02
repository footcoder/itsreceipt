package kr.footcoder.receipt.util;

import kr.footcoder.receipt.enumclass.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Slf4j
public class JsonResultUtil {


    public static JSONObject getFailJson(ErrorCode errorCode){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("status", "F");

            JSONObject error = new JSONObject();
            error.put("error",  errorCode.name());
            error.put("errorCode", errorCode.getErrorMessage());

            jsonObject.put("results", error);
        } catch (JSONException e) {
            log.error("JSONException : {}", e.getMessage());
        }

        return jsonObject;
    }

}
