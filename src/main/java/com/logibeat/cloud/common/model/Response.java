package com.logibeat.cloud.common.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = 4745404731026884662L;

    private boolean success; //调用是否成功

    private T result;       // 如果success = true,则通过result可以获得调用结果

    private String error;   //如果success = false,则通过error可以查看错误信息

    private Integer code;

    private Set<String> warnMessage = new HashSet<String>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Set<String> getWarnMessage() {
        return warnMessage;
    }

    public void setWarnMessage(Set<String> warnMessage) {
        this.warnMessage = warnMessage;
    }

    public void addWarnMessage(String warnMessage) {
        this.warnMessage.add(warnMessage);
    }

    public void addWarnMessage(Collection<String> warnMessage) {
        this.warnMessage.addAll(warnMessage);
    }

    public static <T> Response<T> ok(T data) {
        Response<T> resp = new Response<T>();
        resp.setResult(data);
        return resp;
    }

    public static <T> Response<T> ok() {
        return Response.ok(null);
    }

    public static <T> Response<T> fail(String error) {
        Response<T> resp = new Response<T>();
        resp.setError(error);
        return resp;
    }

    public static <T> Response<T> fail(Integer code, String error) {
        Response<T> resp = fail(error);
        resp.setCode(code);
        return resp;
    }

    public static <T> T orNull(Response<T> response){
        return response.isSuccess() ? response.getResult() : null;
    }

    public static <T> T orDefault(Response<T> response, T defaultValue){
        return response.isSuccess() ? response.getResult() : defaultValue;
    }
}
