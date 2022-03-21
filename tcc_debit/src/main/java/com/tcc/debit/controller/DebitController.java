package com.tcc.debit.controller;

import com.tcc.debit.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;

    @GetMapping("/decrease")
    public void decrease(Long refrence ,String acctNo, String settleAcctNo, BigDecimal amt, String
            clientNo, String settleClientNo) {
        debitService.decrease(acctNo, settleAcctNo, amt, clientNo,settleClientNo);
    }
}
