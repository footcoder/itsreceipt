package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.domain.ReceiptParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.mapper.ReceiptMapper;
import kr.footcoder.receipt.service.ReceiptService;
import kr.footcoder.receipt.util.SessionUserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptMapper receiptMapper;


    public ModelMap receipts(ReceiptParam receiptParam) {
        ModelMap responseMap = new ModelMap();
        responseMap.addAttribute("receipts", receiptMapper.receipts(receiptParam));
        responseMap.addAttribute("receiptCnt", receiptMapper.receiptCnt(receiptParam));
        return responseMap;
    }

    public boolean createReceipt() {
        return false;
    }
}
