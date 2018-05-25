package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.domain.ReceiptParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.mapper.ReceiptMapper;
import kr.footcoder.receipt.service.ReceiptService;
import kr.footcoder.receipt.util.SessionUserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.validation.Valid;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptMapper receiptMapper;


    public ModelMap receipts(ReceiptParam receiptParam) {
        ModelMap responseMap = new ModelMap();
        responseMap.addAttribute("receipts", receiptMapper.receipts(receiptParam));
        responseMap.addAttribute("receiptCnt", receiptMapper.receiptCnt(receiptParam));
        return responseMap;
    }

    public boolean createReceipt(@Valid ReceiptParam receiptParam) {
        int receiptSeq = receiptMapper.createReceipt(receiptParam);

        if(receiptSeq < 0){
            throw new AssertionError("영수증 저장에 실패했습니다.");
        }

        return false;
    }
}
