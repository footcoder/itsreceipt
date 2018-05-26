package kr.footcoder.receipt.mapper;

import kr.footcoder.receipt.domain.Receipt;
import kr.footcoder.receipt.domain.ReceiptParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReceiptMapper {

    List<Receipt> receipts(ReceiptParam receiptParam);

    Integer receiptCnt(ReceiptParam receiptParam);

	int createReceipt(ReceiptParam receiptParam);

    int createReceiptTags(@Param("receiptSeq") int receiptSeq, @Param("tag") String tag);
}
