package kr.footcoder.receipt.domain;

import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

}
