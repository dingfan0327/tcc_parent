package com.seata.bussiness.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("seata-debit-service")
public interface DebitServiceFeign {

    @GetMapping("/debit/decrease")
    public void decrease(@RequestParam("refrence") Long refrence, @RequestParam("acctNo") String acctNo,
                         @RequestParam("settleAcctNo") String settleAcctNo,
                         @RequestParam("amt") BigDecimal amt,
                         @RequestParam("clientNo") String clientNo,
                         @RequestParam("settleClientNo") String settleClientNo);
}
