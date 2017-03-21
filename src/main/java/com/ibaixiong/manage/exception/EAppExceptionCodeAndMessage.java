package com.ibaixiong.manage.exception;

/**
 * Created by Administrator on 2015/3/13.
 */
public enum EAppExceptionCodeAndMessage {
    URL_ERROR(50001, "url 格式错误"),
    NET_OR_JSON_PARSE_ERROR(50002, "网络或者json文档解析错误"),

    Code_And_Other_Token_Validate_Error(90001, "第三方账号登录验证错误"),

    TOKEN_ERROR(10001, "令牌错误"),
    LOGINED_ALREADY_ERROR(10002, "已经登录"),
    LOGIN_OR_PASSWORD_ERROR(10003, "用户名或者密码错误"),
    VARIFICATION_ERROR(10004, "验证码错误或者过期"),
    REG_ERROR(10005, "注册失败，该手机号码已被注册过了，如果是本人请使用密码找回功能"),
    PHONE_ERROR(10006, "手机号码格式不正确请仔细检查"),
    USER_NO_ERROR(10007, "用户没有找到"),
    HX_ERROR(10008, "注册失败远程服务器异常,请联系客服"),
    EMAIL_ERROR(10009, "邮箱格式不正确请仔细检查"),
    ADDRESS_NOT_ID(20001,"地址ID不存在"),
    USER_PWD_UPDATE(20002,"修改密码失败原密码错误"),
    USERCOMPANY_IS_NOTUSER(20003,"提供的帐号没有查找到人员"),
    USERCOMPANY_IS_NOTCOMPANY(20004,"当前登录人员不在任何公司中"),
    USERCOMPANY_IS_NOTALL_GRAND(20005,"当前登录人员没有所有者权限不能进行此次操作"),
    USERCOMPANY_IS_NOT_GRAND1(20006,"当前登录人员是所有者权限不能进行此次操作"),
    USERCOMPANY_IS_LOGINSAME(20007,"自己交接给自己无效操作"),
    USERCOMPANY_IS_USERS(20008,"添加的帐号人员还在其他公司中任职，请先解除掉其他公司中的任职"),
    OTHERLOGIN_ERROR_NOTNULL(20009,"绑定的帐号，密码，或者类型为空"),
    OTHERLOGIN_ERROR_LOGINON(20010,"亲，请更换帐号，该帐号已被注册使用"),
    OTHERLOGIN_ERROR_LOGINNO(20011,"亲，请更换帐号，该帐号在系统中未注册过"),
    USERCOMPANY_ISNOTUSER(20012,"该用户不属于任何公司不可以进行该操作"),
    USER_EMAIL_PHONE_ERROR(20014,"手机号码或者邮箱格式不正确"),
    SERVICE_ID_ERROR(20015,"服务ID错误，不存在"),
    RESUME_ID_ERROR(20016,"简历ID错误，不存在"),
    COMPANY_IS_YES(20017,"添加的公司名称已被注册，请更换公司名称或者联系客服进行确认申诉"),
    COMPANY_IS_NAME_NULL(20018,"公司名称不可为空"),
    COMPANY_IS_ID_NULL(20019,"公司ID不可为空"),
    USERCOMPANY_ACCOUNT_NULL(200013,"帐号或验证码为空");
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private EAppExceptionCodeAndMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
