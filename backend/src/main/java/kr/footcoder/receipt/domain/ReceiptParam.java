package kr.footcoder.receipt.domain;

import lombok.Data;

@Data
public class ReceiptParam {

    //todo @Valid
    private Integer seq;
    private String  tag;
    private Integer userSeq;
    private String  usedDate;
    private String  period;

    private int pageNo; // 페이지 번호
    private int limit = 20; // 한페이지에 보이지는 갯수
    private int offset;
    public int getOffset(){
        return (this.pageNo -1) * this.limit;
    }
}
