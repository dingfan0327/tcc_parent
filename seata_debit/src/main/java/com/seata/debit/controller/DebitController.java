package com.seata.debit.controller;

import com.seata.debit.service.DebitService;
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

    /**
     * @desc 借记交易
     * @param refrence 流水号
     * @param acctNo 出账账户
     * @param settleAcctNo 入账账户
     * @param amt 交易金额
     * @param clientNo 出账账户客户号
     * @param settleClientNo 入账账户客户号
     */
    @GetMapping("/decrease")
    public void decrease(Long refrence ,String acctNo, String settleAcctNo, BigDecimal amt, String
            clientNo, String settleClientNo) {
        debitService.decrease(acctNo, settleAcctNo, amt, clientNo,settleClientNo);
    }
}
