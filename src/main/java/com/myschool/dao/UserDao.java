package com.myschool.dao;

import com.myschool.model.User;

/**
 * @author: huangjinyang
 * @Date: 2018/4/8
 * @Time: 13:37
 * @Description:
 */
public interface UserDao {

    int add(User user);

    int update(User user);
}
