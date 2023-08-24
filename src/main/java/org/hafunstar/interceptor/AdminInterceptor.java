package org.hafunstar.interceptor;


import org.hafunstar.service.imp.BlacklistTokenServiceImp;
import org.hafunstar.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:管理员页面拦截器
 */

public class AdminInterceptor extends HandlerInterceptorAdapter {

    private BlacklistTokenServiceImp tokenService;
    @Autowired
    public void setTokenService(BlacklistTokenServiceImp tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        JwtUtil jwt = new JwtUtil();
        String token = "";
        String username = "";
        //得到cookie
        Cookie[] cookieArr = request.getCookies();

        //得到token
        token = jwt.tokenInCookie(cookieArr);

        //查询此token是否在失效黑名单
        if(tokenService.tokenIsExist(token)){
            response.sendRedirect(request.getContextPath()+"/manager/0");

            return false;
        }
        //验证成功true不拦截false拦截
        if(jwt.verifyToken(token)){
            username = jwt.verify(token).getClaim("username").asString();
            HttpSession session = request.getSession();
            //设置全局用户名
            session.setAttribute("managerAdmin",username);

            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/manager/0");

            return false;
        }
    }

}
