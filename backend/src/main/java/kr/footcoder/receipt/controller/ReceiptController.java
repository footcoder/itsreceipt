package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.enumclass.ErrorCode;
import kr.footcoder.receipt.service.ReceiptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/receipt")
@RestController
@AllArgsConstructor
public class ReceiptController extends BaseController{

	private ReceiptService receiptService;

	/**
	 * 영수증 리스트 조회
	 */
	@PostMapping(value = "/list")
	public ModelMap receiptList(){

		return success().addAttribute("results", receiptService.receiptList());
	}

	/**
	 * 영수증 등록
	 */
	@PostMapping(value = "create")
	public ModelMap createReceipt(){

		if(receiptService.createReceipt()){
			return success();
		}

		return error(ErrorCode.ERR0001);

	}



}
