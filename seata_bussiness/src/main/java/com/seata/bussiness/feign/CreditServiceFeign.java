package com.seata.bussiness.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("seata-credit-service")
public interface CreditServiceFeign {

    @GetMapping("/credit/increase")
    public void increase(@RequestParam("refrence") Long refrence, @RequestParam("acctNo") String acctNo,
                         @RequestParam("settleAcctNo") String settleAcctNo,
                         @RequestParam("amt") BigDecimal amt,
                         @RequestParam("clientNo") String clientNo,
                         @RequestParam("settleClientNo") String settleClientNo);
}
