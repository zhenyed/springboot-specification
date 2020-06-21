package io.zhenye.handler;

import io.zhenye.enums.ResponseCodeEnum;
import io.zhenye.util.ResponseResult;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class SimpleExceptionHandler {

    // 通用异常的处理，返回500
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<?> handleException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ResponseResult.build(ResponseCodeEnum.SC_NOT_FOUND, e.getMessage());
        } else {
            return ResponseResult.build(ResponseCodeEnum.ERROR, e.getMessage());
        }
    }

    // 参数不合法
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            BindException.class,
            HttpMessageNotWritableException.class
    })
    public ResponseResult<?> constraintViolationExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();
        log.error(e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST, e.getMessage());
        }
        return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST);
    }

    // 空结果集
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = {
//            ObjectNotFoundException.class,
//            EmptyResultDataAccessException.class,
//            EmpytCollectionException.class})
    @ResponseBody
    public ResponseResult<?> notFoundExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        return ResponseResult.build(ResponseCodeEnum.SC_NOT_FOUND, e.getMessage());
    }

    // 禁止访问
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
//    @ExceptionHandler(value = {AccessForbiddenException.class})
    @ResponseBody
    public ResponseResult<?> forbiddenExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ResponseResult<?> HttpMessageNotReadableExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        String errorMsg = "";
        if (!StringUtils.isEmpty(e.getCause().getMessage())) {
            int index = e.getCause().getMessage().indexOf("at [Source");
            errorMsg = e.getCause().getMessage().substring(0, index);
        }
        return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST, errorMsg);
    }
}

