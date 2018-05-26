package kr.footcoder.receipt.service.impl;

import kr.footcoder.receipt.domain.Receipt;
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
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptMapper receiptMapper;

    public ModelMap receipts(ReceiptParam receiptParam) {
        ModelMap responseMap = new ModelMap();
        responseMap.addAttribute("receipts", this.getReceipts(receiptParam));
        responseMap.addAttribute("receiptCnt", receiptMapper.receiptCnt(receiptParam));
        return responseMap;
    }

    public boolean createReceipt(@Valid ReceiptParam receiptParam) {

        return this.createReceiptProcess(receiptParam);
    }

    private List<Receipt> getReceipts(ReceiptParam receiptParam){

        List<Receipt> receipts = receiptMapper.receipts(receiptParam);

        for(Receipt receipt : receipts){

            //todo getValue greatCnt, stupidCnt
            //receipt.getSeq();   // redis Key
            receipt.setGreatCnt(1);
            receipt.setStupidCnt(1);
        }

        return receipts;
    }

    private boolean createReceiptProcess(ReceiptParam receiptParam){
        int receiptSeq =  receiptMapper.createReceipt(receiptParam);

        if(receiptSeq < 0){
            throw new AssertionError("영수증 저장에 실패했습니다.");
        }

        log.error("receiptSeq : {}", receiptParam.getSeq());
        String[] tags = receiptParam.getTag().split(",");

        for (String tag : tags) {
            log.error("tag : {}" , tag);
            int tagResult = receiptMapper.createReceiptTags(receiptParam.getSeq(), tag);

            if(tagResult < 0){
                throw new AssertionError("영수증 태그 저장에 실패했습니다.");
            }
        }

        return true;

    }

}
