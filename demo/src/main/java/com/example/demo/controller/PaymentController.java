package com.example.demo.controller;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.exception.EcpayException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final AllInOne allInOne;

    public PaymentController() {
        // 初始化 AllInOne 實例，這裡你需要設置 ECPay 配置
        this.allInOne = new AllInOne("your_configuration_file_path");
    }

    @GetMapping
    public void handlePayment(@RequestParam String tno, @RequestParam String total, HttpServletResponse response)
            throws IOException {
        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(tno);
        obj.setMerchantTradeDate("2024/07/23 09:54:00");
        obj.setTotalAmount(total);
        obj.setTradeDesc("Test Payment lalala");
        obj.setItemName("Frank's Jsp online course");
        obj.setReturnURL("http://localhost:8080/");
        obj.setOrderResultURL("http://localhost:8080/shop/paymentResult");

        String form;
        try {
            form = allInOne.aioCheckOut(obj, null);
        } catch (EcpayException e) {
            e.printStackTrace();
            form = "<html><body><h3>Payment Error</h3></body></html>";
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(form);
    }
}