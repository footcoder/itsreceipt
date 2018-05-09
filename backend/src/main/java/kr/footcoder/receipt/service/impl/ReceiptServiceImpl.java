package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.mapper.ReceiptMapper;
import kr.footcoder.receipt.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptMapper receiptMapper;


    public ModelMap receiptList() {
        ModelMap responseMap = new ModelMap();
        responseMap.addAttribute("receiptList", receiptMapper.receiptList());
        return responseMap;
    }

    @Override
    public boolean createReceipt() {
        return receiptMapper.createReceipt() > 0;
    }
}
