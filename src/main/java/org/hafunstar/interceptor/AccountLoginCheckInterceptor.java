package org.hafunstar.interceptor;


import org.hafunstar.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
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
 * @Description:检查是否登录，登录的用户可以访问的资源
 */

public class AccountLoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username="";
        Cookie[] cookieArr = request.getCookies();
        JwtUtil jwt = new JwtUtil();
        String token = jwt.tokenInCookie(cookieArr);

        if(token.equals("")){

            return false;

        }else {
            if(jwt.verifyToken(token)){

                username = jwt.verify(token).getClaim("username").asString();
                HttpSession session = request.getSession();
                //设置全局用户名
                session.setAttribute("username",username);

                return true;
            }else {

                return false;

            }
        }
    }


}
