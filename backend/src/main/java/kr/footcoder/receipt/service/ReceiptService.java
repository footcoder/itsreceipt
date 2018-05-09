package kr.footcoder.receipt.service;

import org.springframework.ui.ModelMap;

public interface ReceiptService {

    ModelMap receiptList();

    boolean createReceipt();
}
