package com.jjy.manager.service;

import com.jjy.model.vo.system.ValidateCodeVo;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/26
 * @time : 15:26
 */
public interface ValidateCodeService {
    /**
     * 生成图片验证码
     * @return
     */
    ValidateCodeVo generateValidateCode();
}
