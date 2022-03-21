package com.seata.credit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seata.credit.entity.Credit;
import com.seata.credit.mapper.CreditMapper;
import com.seata.credit.service.CreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 贷记服务
 */
@Slf4j
@Service
public class CreditServiceImpl extends ServiceImpl<CreditMapper, Credit> implements CreditService {

    public void increase(String acctNo, String settleAcctNo, BigDecimal tranAmt, String clientNo,
                         String settleClientNo) {
        QueryWrapper<Credit> wrapper = new QueryWrapper<Credit>();
        wrapper.lambda().eq(Credit::getAcctNo, settleAcctNo);
        wrapper.lambda().eq(Credit::getClientNo, settleClientNo);
        Credit userCredit = this.getOne(wrapper);
        if (userCredit == null) {
            throw new RuntimeException("账户不存在，或已经销户！");
        }
        userCredit.setAmt(userCredit.getAmt().add(tranAmt));//设置冻结金额
        this.saveOrUpdate(userCredit);
    }

}
