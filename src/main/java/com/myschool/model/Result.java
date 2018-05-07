package com.myschool.model;

/**
 * @author : sujinpeng
 * @Date : 2017/12/25
 * @Time : 16:44
 * @Description : 返回信息
 */
public class Result<T> {
    /**
     * 默认0代表成功
     */
    private int code = 0;

    /**
     * 错误信息
     */
    private String msg = "";

    /**
     * data数据
     */
    private T data;

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
}
