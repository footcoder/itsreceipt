package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.common.CommonUtils;
import kr.footcoder.receipt.domain.ReceiptParam;
import kr.footcoder.receipt.domain.User;
import kr.footcoder.receipt.enumclass.ErrorCode;
import kr.footcoder.receipt.service.ReceiptService;
import kr.footcoder.receipt.util.SessionUserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RequestMapping("/receipts")
@RestController
@AllArgsConstructor
public class ReceiptController extends BaseController {

	private ReceiptService receiptService;

	/**
	 * 영수증 리스트 조회
	 */
	@GetMapping(value = "")
	public ModelMap receipts(
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo
			, @RequestParam(value = "tag"	, required = false, defaultValue = "") String tag
			, @RequestParam(value = "period", required = false, defaultValue = "") String period
	) {
		ReceiptParam receiptParam = new ReceiptParam();
		receiptParam.setPageNo(pageNo);
		receiptParam.setTag(tag);

		if( !StringUtils.isEmpty(period)){
			receiptParam.setPeriod(CommonUtils.getPeriod(period));
		}

		return success().addAttribute("results", receiptService.receipts(receiptParam));
	}

	/**
	 * 영수증 등록
	 */
	@PostMapping(value = "")
	public ModelMap createReceipt(@Valid ReceiptParam receiptParam) {

		receiptParam.setUserSeq(SessionUserUtil.getUser().getSeq());

		if (receiptService.createReceipt(receiptParam)) {
			return success();
		}

		return error(ErrorCode.ERR0001);

	}


}
