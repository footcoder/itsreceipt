package kr.footcoder.receipt.domain;

import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
public class AuthenticationRequest {

    private String authorization;
    private String email;
    private String password;

    public void setAuthorization(String authorization){

        String[] decodedAuthorization = new String(Base64.decodeBase64(authorization)).split(":");
        this.email      =  decodedAuthorization[0];
        this.password   =  decodedAuthorization[1];
    }

}
