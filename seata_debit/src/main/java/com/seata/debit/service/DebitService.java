package com.seata.debit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seata.debit.entity.Debit;
import io.seata.rm.tcc.api.BusinessActionContextParameter;

import java.math.BigDecimal;

/**
 * 借记记交易。
 */
public interface DebitService extends IService<Debit> {

    /**
     * @desc 借记交易
     * @param acctNo 出账账户
     * @param settleAcctNo 入账账户
     * @param amt 交易金额
     * @param clientNo 出账账户客户号
     * @param settleClientNo 入账账户客户号
     */
    public void decrease(@BusinessActionContextParameter(paramName = "acctNo")String acctNo,
                         @BusinessActionContextParameter(paramName = "settleAcctNo") String settleAcctNo,
                         @BusinessActionContextParameter(paramName = "amt") BigDecimal amt,
                         @BusinessActionContextParameter(paramName = "clientNo") String clientNo,
                         @BusinessActionContextParameter(paramName = "settleClientNo") String settleClientNo);


}