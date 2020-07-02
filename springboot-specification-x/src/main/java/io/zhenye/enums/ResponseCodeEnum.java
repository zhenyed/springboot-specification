package io.zhenye.enums;

public enum ResponseCodeEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "服务器错误"),
    BAD_REQUEST(400, "参数错误"),
    SC_NOT_FOUND(404, "资源不存在"),
    FORBIDDEN(403, "禁止访问"),
    NOT_LOGIN(1000, "未登录"),
    LOGGED_IN(1001, "已登录"),
    NOT_AUTH(2000, "未授权"),
    AUTH_EXPIRE(2001, "时间戳过期"),
    AUTH_ERROR(2002, "检验失败"),
    ;

    private int code;
    private String desc;

    ResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
