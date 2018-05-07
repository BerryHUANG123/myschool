package com.myschool.expection;


/**
 * @author : sujinpeng
 * @Date : 2017/12/25
 * @Time : 21:34
 * @Description : 服务类异常
 */
public class ServiceException extends Exception {
    private Integer code = -1;
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
