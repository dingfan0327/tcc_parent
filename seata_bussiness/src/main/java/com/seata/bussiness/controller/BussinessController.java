package com.seata.bussiness.controller;

import com.seata.bussiness.service.BussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/withdrawal")
public class BussinessController {

    @Autowired
    private BussinessService bussinessService;

    @GetMapping("/sucess")
    public String withdrawal() {
        bussinessService.withdrawal("6100010000100001", "6100010000100002",new BigDecimal("100"), "100001","100001");
        return "success";
    }

    @GetMapping("/error")
    public String withdrawalat() {
        bussinessService.withdrawal("6100010000100001", "6100010000100002",new BigDecimal("100"), "100001","100002");
        return "error";
    }
}
