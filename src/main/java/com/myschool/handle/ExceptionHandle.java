package com.myschool.handle;

import com.myschool.expection.ServiceException;
import com.myschool.model.Result;
import com.myschool.utils.ResultUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : sujinpeng
 * @Date : 2017/12/25
 * @Time : 20:37
 * @Description : 全局异常处理器
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LogManager.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return ResultUtil.error(serviceException.getCode(), serviceException.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(-1, "出问题啦!");
            // TODO: 2018/2/1 记录 每天收集统一修改
        }
    }
}
