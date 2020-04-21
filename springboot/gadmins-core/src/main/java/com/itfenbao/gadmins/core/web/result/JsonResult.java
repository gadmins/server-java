package com.itfenbao.gadmins.core.web.result;

/**
 * json 结果
 *
 * @param <T>
 * @author itfenbao
 */
public class JsonResult<T> implements IResult {
    private Integer code;
    private String msg;
    private T data;

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
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


    @Override
    public String toString() {
        return "code=" + code + " message=" + msg + " data=" + data;
    }

    public static <T> JsonResult<T> fail() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.FAIL.getCode());
        ret.setMsg(JsonReturnCode.FAIL.getDesc());
        return ret;
    }

    public static JsonResult fail(String msg) {
        JsonResult ret = JsonResult.fail();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> fail(T data) {
        JsonResult<T> ret = JsonResult.fail();
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> failToken() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.FAIL_TOKEN.getCode());
        ret.setMsg(JsonReturnCode.FAIL_TOKEN.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> failMessage(String msg) {
        JsonResult<T> ret = JsonResult.fail();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> success() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.SUCCESS.getCode());
        ret.setMsg(JsonReturnCode.SUCCESS.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> success(String msg) {
        JsonResult<T> ret = JsonResult.success();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> ret = JsonResult.success();
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        JsonResult<T> ret = JsonResult.success();
        ret.setMsg(msg);
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> paramsError() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.PARAMETER_ERROR.getCode());
        ret.setMsg(JsonReturnCode.PARAMETER_ERROR.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> paramsErrorMessage(String msg) {
        JsonResult<T> ret = JsonResult.paramsError();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> http404() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.NOT_FOUND.getCode());
        ret.setMsg(JsonReturnCode.NOT_FOUND.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> http404Message(String msg) {
        JsonResult<T> ret = JsonResult.http404();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> http404(T data) {
        JsonResult<T> ret = JsonResult.http404();
        ret.setData(data);
        return ret;
    }

    public static JsonResult http403() {
        JsonResult ret = new JsonResult();
        ret.setCode(JsonReturnCode.ACCESS_ERROR.getCode());
        ret.setMsg(JsonReturnCode.ACCESS_ERROR.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> http403(T data) {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.ACCESS_ERROR.getCode());
        ret.setMsg(JsonReturnCode.ACCESS_ERROR.getDesc());
        ret.setData(data);
        return ret;
    }

    public static <T> JsonResult<T> http500() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.INTERNAL_SERVER_ERROR.getCode());
        ret.setMsg(JsonReturnCode.INTERNAL_SERVER_ERROR.getDesc());
        return ret;
    }

    public static <T> JsonResult<T> http500(String msg) {
        JsonResult<T> ret = JsonResult.http500();
        ret.setMsg(msg);
        return ret;
    }

    public static <T> JsonResult<T> noLogin() {
        JsonResult<T> ret = new JsonResult<T>();
        ret.setCode(JsonReturnCode.NOT_LOGIN.getCode());
        ret.setMsg(JsonReturnCode.NOT_LOGIN.getDesc());
        return ret;
    }
}
