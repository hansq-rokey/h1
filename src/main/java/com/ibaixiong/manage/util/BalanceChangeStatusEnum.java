package com.ibaixiong.manage.util;

/**
 * created by 重剑 on 2016/12/14
 *
 * 订单状态的枚举
 *
 */
public enum BalanceChangeStatusEnum {

    /** 追加 */
    ADDBANLANCE(0),

    /** 扣除 */
    MINUSBALANCE(1),
    
    /**提货款支付*/
    FIRSTBALANCE(2),
    
    /**余额支付*/
    PAYBALANCE(3),
    
    /**第三方支付*/
    THIRDBALANCE(4);
    

    public byte getCode() {
        return this.code;
    }

    private byte code;


    private BalanceChangeStatusEnum(int code) {
        this.code = (byte)code;
    }
}
