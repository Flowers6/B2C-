package com.jjy.common.exception;

import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 21:46
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null, ResultCodeEnum.DATA_ERROR);
    }

    //自定义异常处理
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Result error(CustomException e) {
        e.printStackTrace();
        return Result.build(null, e.getResultCodeEnum());
    }
}
