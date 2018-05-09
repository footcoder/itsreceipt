package kr.footcoder.receipt.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.footcoder.receipt.domain.ReceiptParam;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class ReceiptControllerRest {

    private final MockMvc mvc;
    private final ReceiptParam receiptParam;

    public ReceiptControllerRest(final MockMvc mvc, ReceiptParam receiptParam) {
        this.mvc = mvc;
        this.receiptParam = receiptParam;
    }

    public MockHttpServletResponse receiptList() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return this.mvc.perform(
                post("/receipt/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptParam))
        ).andReturn().getResponse();
    }

    public MockHttpServletResponse createReceipt() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return this.mvc.perform(
                post("/receipt/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptParam))
        ).andReturn().getResponse();
    }

}
