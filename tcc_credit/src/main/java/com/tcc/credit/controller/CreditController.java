package com.tcc.credit.controller;

import com.tcc.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/increase")
    public void increase(Long refrence,String acctNo, String settleAcctNo, BigDecimal amt, String
            clientNo, String settleClientNo) {
        creditService.increase(acctNo, settleAcctNo, amt,
                clientNo,settleClientNo);
    }
}
