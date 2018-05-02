package kr.footcoder.receipt.controller;

import kr.footcoder.receipt.enumclass.ErrorCode;
import kr.footcoder.receipt.exceptions.LogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Controller
public class BaseController {

    @ExceptionHandler(LogicException.class)
    public ModelMap handleLogicException(LogicException ex) {
        log.debug(ex.getMessage());
        return error(ex.getErrorCode());
    }

    protected ModelMap success() {
        return new ModelMap("status", "T");
    }

    protected ModelMap error(ErrorCode errorCode) {

        ModelMap error = new ModelMap();
        error.addAttribute("status", "F");
        error.addAttribute("code", errorCode.name());
        error.addAttribute("message", errorCode.getErrorMessage());

        return error;
    }

    protected ModelMap error(ErrorCode errorCode, String message) {
        ModelMap error = error(errorCode);
        error.addAttribute("message", message);

        return error;
    }

    protected ModelMap error(ErrorCode errorCode, DuplicateKeyException ex) {
        log.error(ex.getMessage(), ex);
        return error(errorCode);
    }

    protected ModelMap error(ErrorCode errorCode, String key, Object obj) {
        ModelMap error = error(errorCode);
        error.addAttribute(key, obj);

        return error;
    }

}
