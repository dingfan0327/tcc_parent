package com.seata.credit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seata.credit.entity.Credit;
import io.seata.rm.tcc.api.BusinessActionContextParameter;


import java.math.BigDecimal;

/**
 *  贷记交易
 */
public interface CreditService extends IService<Credit> {

    /**
     * 转账支取
     *
     * @param acctNo  出账账号
     * @param settleAcctNo 入账金额
     * @param amt 支取金额
     * @param clientNo  出账账户客户号
     * @param settleClientNo 入账账户客户号
     */
    public void increase(@BusinessActionContextParameter(paramName = "acctNo") String acctNo,
                         @BusinessActionContextParameter(paramName = "settleAcctNo") String settleAcctNo,
                         @BusinessActionContextParameter(paramName = "amt") BigDecimal amt,
                         @BusinessActionContextParameter(paramName = "clientNo") String clientNo,
                         @BusinessActionContextParameter(paramName = "settleClientNo") String settleClientNo);

}