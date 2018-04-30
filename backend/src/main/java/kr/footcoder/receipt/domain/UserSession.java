package kr.footcoder.receipt.domain;

import lombok.Data;

@Data
public class UserSession {

    private int seq;
    private String email;
    private String password;
    private String role;
}
