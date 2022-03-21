package com.tcc.bussiness.service;

import java.math.BigDecimal;

public interface BussinessService {

    public void withdrawal(String acctNo, String settleAcctNo, BigDecimal amt, String clientNo, String settleClientNo);

}
