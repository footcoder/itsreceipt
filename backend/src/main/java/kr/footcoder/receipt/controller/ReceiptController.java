package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.domain.ReceiptParam;
import kr.footcoder.receipt.enumclass.ErrorCode;
import kr.footcoder.receipt.service.ReceiptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
			, @RequestParam(value = "tag", required = false, defaultValue = "") String tag
	) {
		ReceiptParam receiptParam = new ReceiptParam();
		receiptParam.setPageNo(pageNo);
		receiptParam.setTag(tag);

		return success().addAttribute("results", receiptService.receipts(receiptParam));
	}

	/**
	 * 영수증 등록
	 */
	@PostMapping(value = "/create")
	public ModelMap createReceipt(@RequestBody ReceiptParam receiptParam) {

		if (receiptService.createReceipt()) {
			return success();
		}

		return error(ErrorCode.ERR0001);

	}


}
