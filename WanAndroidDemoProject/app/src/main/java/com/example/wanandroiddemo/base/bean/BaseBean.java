package com.example.wanandroiddemo.base.bean;

import java.io.Serializable;

/**
 * @author LPL
 * @version V1.0
 * @ClassName: BaseBean
 * @Description: 实体类基类
 * @date 2021/5/30 23:50
 */
public class BaseBean<T> implements Serializable {
    /**
     * 错误码
     */
    public int errorCode;

    /**
     * 错误信息
     */
    public String errorMsg;

    /**
     * 数据
     */
    public T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
