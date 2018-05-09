package kr.footcoder.receipt.domain;

import lombok.Data;

@Data
public class Receipt {
    private Integer seq;
    private String createDate;
    private String usedDate;
    private String email;
}
