package kr.footcoder.receipt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/receipt")
public class ReceiptController extends BaseController{


	@PostMapping(value = "/list")
	public ModelMap receiptList(){

		log.error("receipt/list 호출성공");

		return success();
	}



}
