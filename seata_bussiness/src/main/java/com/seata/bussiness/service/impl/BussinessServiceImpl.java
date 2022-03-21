package com.seata.bussiness.service.impl;

import com.seata.bussiness.feign.CreditServiceFeign;
import com.seata.bussiness.feign.DebitServiceFeign;
import com.seata.bussiness.service.BussinessService;
import com.seata.bussiness.utils.IdWorker;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 业务逻辑
 */
@Service
public class BussinessServiceImpl implements BussinessService {

    @Autowired
    CreditServiceFeign creditServiceFeign;
    @Autowired
    DebitServiceFeign debitServiceFeign;

    @Autowired
    IdWorker idWorker;

    /**
     * 转账支取
     *
     * @param acctNo  出账账号
     * @param settleAcctNo 入账金额
     * @param amt 支取金额
     * @param clientNo  出账账户客户号
     * @param settleClientNo 入账账户客户号
     */
    public void withdrawal(String acctNo, String settleAcctNo,BigDecimal amt, String
            clientNo, String settleClientNo) {
        //交易流水
        long refrence = idWorker.nextId();

        //先借记事件
        debitServiceFeign.decrease(refrence,acctNo, settleAcctNo,amt, clientNo,settleClientNo);
        //后贷记事件
        creditServiceFeign.increase(refrence,acctNo, settleAcctNo,amt, clientNo,settleClientNo);

    }


}
