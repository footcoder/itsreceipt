package kr.footcoder.receipt.domain;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

}
