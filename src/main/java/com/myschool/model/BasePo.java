package com.myschool.model;

import java.util.Date;

/**
 * @author : sujinpeng
 * @Date : 2017/12/25
 * @Time : 13:45
 * @Description : base po类，所有jopo继承此类
 */
public class BasePo {
    /**
     *创建时间
     */
    protected Date createTime;

    /**
     *更新的时间
     */
    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
