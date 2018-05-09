package kr.footcoder.receipt.mapper;

import kr.footcoder.receipt.domain.Receipt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReceiptMapper {

    List<Receipt> receiptList();

    int createReceipt();
}
