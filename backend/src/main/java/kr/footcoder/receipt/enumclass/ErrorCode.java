package kr.footcoder.receipt.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 정의 에러코드
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

    ERR0001("오류가 발생하였습니다."),
    ERR0002("이미 회원가입된 이메일 계정입니다."),
    ERR0003("회원정보가 일치하지 않습니다. 확인해주세요."),
    ERR0004("토큰이 만료되었습니다. 재로그인 바랍니다.");


    private String errorMessage;

}
