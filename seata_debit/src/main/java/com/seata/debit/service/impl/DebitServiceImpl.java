package com.seata.debit.service.impl;


import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seata.debit.entity.Debit;
import com.seata.debit.mapper.DebitMapper;
import com.seata.debit.service.DebitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 借记交易
 */
@Slf4j
@Service
public class DebitServiceImpl extends ServiceImpl<DebitMapper, Debit> implements DebitService {

    public void decrease(String acctNo, String settleAcctNo, BigDecimal tranAmt, String clientNo,
                         String settleClientNo) {
        QueryWrapper<Debit> wrapper = new QueryWrapper<Debit>();
        wrapper.lambda().eq(Debit::getAcctNo, acctNo);
        wrapper.lambda().eq(Debit::getClientNo, clientNo);
        Debit userCredit = this.getOne(wrapper);
        if (userCredit == null) {
            throw new RuntimeException("账户不存在，或已经销户！");
        }
        userCredit.setAmt(userCredit.getAmt().subtract(tranAmt));//设置冻结金额
        this.saveOrUpdate(userCredit);

    }
}