package com.jjy.pay.service;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:19
 */
public interface AlipayService {
    /**
     * 提交数据返回表单
     * @param orderNo
     * @return
     */
    String submitAlipay(String orderNo);
}
