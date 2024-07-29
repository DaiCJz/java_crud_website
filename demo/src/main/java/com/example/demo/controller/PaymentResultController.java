package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/paymentResult")
public class PaymentResultController {

    @GetMapping
    @PostMapping
    public ModelAndView handleRequest(@RequestParam Map<String, String> allParams) {
        ModelAndView modelAndView = new ModelAndView("result"); // 指定 view 的名稱

        // 將參數傳遞到視圖中
        modelAndView.addObject("params", allParams);

        return modelAndView;
    }
}
