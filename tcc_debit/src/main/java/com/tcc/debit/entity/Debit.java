package com.tcc.debit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户余额实体类
 */
@Data
@TableName("t_acct_balance")
public class Debit {

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;//ID
    @TableField
    private BigDecimal frozenAmt;
    @TableField
    private String acctNo;
    @TableField
    private BigDecimal amt;
    @TableField
    private String clientNo;
}
