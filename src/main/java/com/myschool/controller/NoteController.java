package com.myschool.controller;

import com.myschool.model.Result;
import com.myschool.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Berry on 2018/4/30.
 */
@Controller
@RequestMapping("note")
public class NoteController {

    @RequestMapping("page")
    public ModelAndView page() {
        return new ModelAndView("note/page");
    }

    public Result pageData() {
        return ResultUtil.success();
    }

    @RequestMapping("add")
    @ResponseBody
    public Result add() {
        return ResultUtil.success();
    }
}
