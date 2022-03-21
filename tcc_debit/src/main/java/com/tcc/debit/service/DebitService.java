package com.tcc.debit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tcc.debit.entity.Debit;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @LocalTCC 该注解需要添加到上⾯描述的接⼝上，表示实现该接⼝的类被  seata 来管 理，  seata 根据事务的状态，
 * ⾃动调⽤我们定义的⽅法，如果没问题则调⽤  Commit ⽅法，否则调⽤  Rollback ⽅ 法。
 */
@LocalTCC
public interface DebitService extends IService<Debit> {

    /**
     * @TwoPhaseBusinessAction 描述⼆阶段提交
     * name: 为  tcc⽅法的  bean 名称，需要全局唯⼀，⼀般写⽅法名即可
     * commitMethod: Commit⽅法的⽅法名
     * rollbackMethod:Rollback⽅法的⽅法名
     * @BusinessActionContextParamete 该注解⽤来修饰  Try⽅法的⼊参，
     * 被修饰的⼊参可以在  Commit ⽅法和  Rollback ⽅法中通过
     * BusinessActionContext 获取。
     */
    @TwoPhaseBusinessAction(name = "decreaseTcc", commitMethod = "decreaseCommit", rollbackMethod = "decreaseRollback")
    public void decrease(@BusinessActionContextParameter(paramName = "acctNo")String acctNo,
                         @BusinessActionContextParameter(paramName = "settleAcctNo") String settleAcctNo,
                         @BusinessActionContextParameter(paramName = "amt") BigDecimal amt,
                         @BusinessActionContextParameter(paramName = "clientNo") String clientNo,
                         @BusinessActionContextParameter(paramName = "settleClientNo") String settleClientNo);

    public boolean decreaseCommit(BusinessActionContext context);

    public boolean decreaseRollback(BusinessActionContext context);


}