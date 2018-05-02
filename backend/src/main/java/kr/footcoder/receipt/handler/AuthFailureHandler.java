package kr.footcoder.receipt.handler;

import kr.footcoder.receipt.enumclass.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.error("로그인 실패 onAuthenticationFailure");
        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("status", "F");

            JSONObject error = new JSONObject();
            error.put("error", ErrorCode.ERR0003.name());
            error.put("errorCode", ErrorCode.ERR0003.getErrorMessage());

            jsonObject.put("results", error);
        } catch (JSONException e) {
            log.error("JSONException : {}", e.getMessage());
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(jsonObject);
        writer.flush();
        writer.close();

    }

}
