package io.zhenye.util;

import io.zhenye.enums.ResponseCodeEnum;

public class ResponseResult<T> {

    private int code;

    private String msg;

    private T data;

    private long currentTime = System.currentTimeMillis();

    public ResponseResult() {
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getDesc();
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum, T data) {
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getDesc();
        this.data = data;
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum, String msg) {
        this.code = responseCodeEnum.getCode();
        this.msg = msg;
    }

    private ResponseResult(ResponseCodeEnum responseCodeEnum, String msg, T data) {
        this.code = responseCodeEnum.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseCodeEnum.SUCCESS);
    }

    public static ResponseResult error() {
        return new ResponseResult(ResponseCodeEnum.ERROR);
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult(ResponseCodeEnum.ERROR, msg, null);
    }

    public static ResponseResult build(ResponseCodeEnum responseCodeEnum) {
        return new ResponseResult(responseCodeEnum);
    }

    public static <T> ResponseResult<T> build(ResponseCodeEnum responseCodeEnum, T data) {
        return new ResponseResult(responseCodeEnum, data);
    }

    public static ResponseResult build(ResponseCodeEnum responseCodeEnum, String msg) {
        return new ResponseResult(responseCodeEnum, msg);
    }

    public static <T> ResponseResult<T> build(ResponseCodeEnum responseCodeEnum, String msg, T data) {
        return new ResponseResult(responseCodeEnum, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCurrentTime() {
        return currentTime;
    }

}
