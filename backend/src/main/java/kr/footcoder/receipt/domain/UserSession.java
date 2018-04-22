package kr.footcoder.receipt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSession {

    private String email;
    private String password;
    private String role;
}
