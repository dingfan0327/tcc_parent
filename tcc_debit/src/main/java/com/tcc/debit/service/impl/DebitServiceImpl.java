package com.tcc.debit.service.impl;


import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcc.debit.entity.Debit;
import com.tcc.debit.mapper.DebitMapper;
import com.tcc.debit.service.DebitService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 */
@Slf4j
@Service
public class DebitServiceImpl extends ServiceImpl<DebitMapper, Debit> implements DebitService {

    /**
     * 转账贷记事件
     *
     * @param acctNo       账户
     * @param settleAcctNo 结算账号
     * @param tranAmt      交易金额
     * @param clientNo     客户号
     * @return
     */
    public void decrease(String acctNo, String settleAcctNo, BigDecimal tranAmt, String clientNo,
                         String settleClientNo) {
        QueryWrapper<Debit> wrapper = new QueryWrapper<Debit>();
        wrapper.lambda().eq(Debit::getAcctNo, acctNo);
        wrapper.lambda().eq(Debit::getClientNo, clientNo);
        Debit userCredit = this.getOne(wrapper);
        if (userCredit == null) {
            throw new RuntimeException("账户不存在，或已经销户！");
        }
        userCredit.setFrozenAmt(tranAmt);//设置冻结金额
        this.saveOrUpdate(userCredit);

    }

    /**
     * 借记提交
     *
     * @param context 上下文
     * @return
     */
    @Override
    public boolean decreaseCommit(BusinessActionContext context) {
        //查询账户信息
        QueryWrapper<Debit> wrapper = new QueryWrapper<Debit>();
        wrapper.lambda().eq(Debit::getAcctNo,
                context.getActionContext("acctNo"));
        wrapper.lambda().eq(Debit::getClientNo,
                context.getActionContext("clientNo"));
        Debit userCredit = this.getOne(wrapper);
        if (userCredit != null) {
            //增加⽤户余额
            userCredit.setAmt(userCredit.getAmt().subtract(userCredit.getFrozenAmt()));
            //冻结金额清零
            userCredit.setFrozenAmt(BigDecimal.ZERO);
            this.saveOrUpdate(userCredit);
        }
        log.info("--------->tcc=" + context.getXid() + " 提交成功!");
        return true;
    }

    /**
     * 借记回滚
     *
     * @param context 上下文
     * @return
     */
    @Override
    public boolean decreaseRollback(BusinessActionContext context) {
        //查询⽤户余额
        QueryWrapper<Debit> wrapper = new QueryWrapper<Debit>();
        wrapper.lambda().eq(Debit::getAcctNo,
                context.getActionContext("acctNo"));
        wrapper.lambda().eq(Debit::getClientNo,
                context.getActionContext("clientNo"));
        Debit userCredit = this.getOne(wrapper);
        if (userCredit != null) {
            //冻结金额清零
            userCredit.setFrozenAmt(BigDecimal.ZERO);
            this.saveOrUpdate(userCredit);
        }
        log.info("--------->tcc=" + context.getXid() + " 回滚成功!");
        return true;
    }
}