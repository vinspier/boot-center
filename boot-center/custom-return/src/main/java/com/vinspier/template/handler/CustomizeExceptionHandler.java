package com.vinspier.template.handler;

import com.alibaba.fastjson.JSONObject;
import com.vinspier.template.common.ResponseTemplate;
import com.vinspier.template.common.ResultCode;
import com.vinspier.template.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: CustomizeExceptionHandler
 * @Description: 自定义全局异常处理类
 * @Author:
 * @Date: 2020/3/19 11:29
 * @Version V1.0
 **/

@Slf4j
@RestControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseTemplate handleException(Exception e){
        log.error(e.getMessage(),e);
        return ResponseTemplate.failed(ResultCode.SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomizeException.class)
    public ResponseTemplate handleUserException(CustomizeException e){
        log.error(e.getMessage(),e);
        return ResponseTemplate.failed(e.getResultCode());
    }

}
