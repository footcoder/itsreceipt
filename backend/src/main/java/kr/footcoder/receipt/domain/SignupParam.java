package kr.footcoder.receipt.domain;

import lombok.Data;

@Data
public class SignupParam {


    private String email;
    private String password;
    private String moneyType;

}
