package com.myschool.interceptor;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class SysUserFilter extends PathMatchingFilter {

   /* @Autowired
    private IUserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        SessionUser sessionUser = WebUtil.getCurrentUser();
        if (null != sessionUser) {
            User user = sessionUser.getUser();
            // 记录用户登录时间和登录IP
            Date now = new Date();
            Date loginTime = user.getLoginTime();
            if (loginTime == null || !DateUtil.format(now).equals(DateUtil.format(loginTime))) {
                user = userService.get(user.getId());
                user.setLastLoginTime(user.getLoginTime());
                user.setLoginTime(now);

                user.setLastLoginIp(user.getLoginIp());
                user.setLoginIp(request.getRemoteAddr());
                userService.update(user);

                sessionUser.setUser(user);
                WebUtil.saveCurrentUser(sessionUser);
            }
        }
        return true;
    }*/
}