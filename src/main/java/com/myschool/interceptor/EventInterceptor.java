package com.myschool.interceptor;

import com.myschool.utils.DateUtil;
import org.springframework.core.NamedThreadLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 */
public class EventInterceptor extends BaseInterceptor {

	private final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 开始时间（该数据只有当前请求的线程可见）
		startTimeThreadLocal.set(System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 保存日志
		String message = "开始时间: {}; 结束时间: {}; 耗时: {}s; URI: {}; ";
        long startTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
		logger.info(message, DateUtil.format(startTime, "HH:mm:ss.SSS"),
				DateUtil.format(endTime, "HH:mm:ss.SSS"),
				(endTime - startTime) / 1000.00, request.getServletPath());
		super.afterCompletion(request, response, handler, ex);
	}
}