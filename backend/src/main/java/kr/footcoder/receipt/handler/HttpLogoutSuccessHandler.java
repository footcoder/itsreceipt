package kr.footcoder.receipt.handler;

import kr.footcoder.receipt.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class HttpLogoutSuccessHandler  implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        if ( authentication == null ) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            User loginUserDetails = (User) authentication.getPrincipal();
            PrintWriter pw = response.getWriter();
            pw.write(loginUserDetails.getUsername() + " logout success");
            pw.flush();
        }

    }

}
