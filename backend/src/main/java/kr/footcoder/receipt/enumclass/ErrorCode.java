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
    ERR0002("이미 회원가입된 이메일 계정입니다.");

    private String errorMessage;

}
