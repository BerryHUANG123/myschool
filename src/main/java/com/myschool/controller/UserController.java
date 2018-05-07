package com.myschool.controller;

import com.myschool.dao.UserDao;
import com.myschool.model.Result;
import com.myschool.model.User;
import com.myschool.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author :sujinpeng
 * @Date :2018/4/4
 * @Time :14:05
 * @Description :用户控制器
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("page")
    public ModelAndView page() {
        return new ModelAndView("page");
    }

    @RequestMapping("add")
    @ResponseBody
    public Result add(User user) {
        return ResultUtil.success(userDao.add(user));
    }
}
