package kr.footcoder.receipt.service;

import kr.footcoder.receipt.domain.ReceiptParam;
import org.springframework.ui.ModelMap;

public interface ReceiptService {

    ModelMap receipts(ReceiptParam receiptParam);

    boolean createReceipt(ReceiptParam receiptParam);
}
