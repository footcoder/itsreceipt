package kr.footcoder.receipt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.footcoder.receipt.controller.rest.ReceiptControllerRest;
import kr.footcoder.receipt.domain.ReceiptParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ReceiptControllerTest {


    @Autowired
    private WebApplicationContext context;
    private ReceiptControllerRest receiptControllerRest;




    @Before
    public void setUp() {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        ReceiptParam receiptParam = new ReceiptParam();

        receiptControllerRest = new ReceiptControllerRest(mvc, receiptParam);
    }

    @Test
    public void 영수증_리스트_조회() throws Exception {
        System.out.println(receiptControllerRest.receiptList().getContentAsString());
    }

    @Test
    public void 영수증_입력() throws Exception {
        System.out.println(receiptControllerRest.receiptList().getContentAsString());
    }

}
