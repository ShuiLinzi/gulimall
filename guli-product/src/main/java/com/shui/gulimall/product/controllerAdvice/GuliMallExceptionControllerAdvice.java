package com.shui.gulimall.product.controllerAdvice;

import com.shui.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.shui.gulimall.product.controller")
public class GuliMallExceptionControllerAdvice {

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error(throwable.getMessage());
        return R.error();

    }
}
