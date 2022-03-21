package com.tcc.credit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcc.credit.entity.Credit;
import com.tcc.credit.mapper.CreditMapper;
import com.tcc.credit.service.CreditService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 贷记服务
 */
@Slf4j
@Service
public class CreditServiceImpl extends ServiceImpl<CreditMapper, Credit> implements CreditService {

    /**
     * 转账支取贷记事件
     *
     * @param acctNo       ⽤户名
     * @param settleAcctNo 结算账户
     * @param tranAmt      交易金额
     * @param clientNo     客户号
     * @return
     */
    public void increase(String acctNo, String settleAcctNo, BigDecimal tranAmt, String clientNo,
                         String settleClientNo) {
        QueryWrapper<Credit> wrapper = new QueryWrapper<Credit>();
        wrapper.lambda().eq(Credit::getAcctNo, settleAcctNo);
        wrapper.lambda().eq(Credit::getClientNo, settleClientNo);
        Credit userCredit = this.getOne(wrapper);
        if (userCredit == null) {
            throw new RuntimeException("账户不存在，或已经销户！");
        }
        userCredit.setFrozenAmt(tranAmt);//设置冻结金额
        this.saveOrUpdate(userCredit);
    }

    /**
     * 贷记提交
     *
     * @param context 上下文
     * @return
     */
    @Override
    public boolean increaseCommit(BusinessActionContext context) {
        //查询账户信息
        QueryWrapper<Credit> wrapper = new QueryWrapper<Credit>();
        wrapper.lambda().eq(Credit::getAcctNo,
                context.getActionContext("settleAcctNo"));
        wrapper.lambda().eq(Credit::getClientNo,
                context.getActionContext("settleClientNo"));
        Credit userCredit = this.getOne(wrapper);
        if (userCredit != null) {
            //增加⽤户余额
            userCredit.setAmt(userCredit.getAmt().add(userCredit.getFrozenAmt()));
            //冻结金额清零
            userCredit.setFrozenAmt(BigDecimal.ZERO);
            this.saveOrUpdate(userCredit);
        }
        return true;
    }

    /**
     * 贷记回退
     *
     * @param context 上下文
     * @return
     */
    @Override
    public boolean increaseRollback(BusinessActionContext context) {
        //查询⽤户余额
        QueryWrapper<Credit> wrapper = new QueryWrapper<Credit>();
        wrapper.lambda().eq(Credit::getAcctNo,
                context.getActionContext("settleAcctNo"));
        wrapper.lambda().eq(Credit::getClientNo,
                context.getActionContext("settleClientNo"));
        Credit userCredit = this.getOne(wrapper);
        if (userCredit != null) {
            //冻结金额清零
            userCredit.setFrozenAmt(BigDecimal.ZERO);
            this.saveOrUpdate(userCredit);
        }
        log.info("--------->tcc=" + context.getXid() + " 回滚成功!");
        return true;
    }
}
