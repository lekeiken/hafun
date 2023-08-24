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
 * @Description:区分是游客还是用户
 */

public class IndexInterceptor extends HandlerInterceptorAdapter {
    private BlacklistTokenServiceImp tokenService;
    @Autowired
    public void setTokenService(BlacklistTokenServiceImp tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username="";
        JwtUtil jwt = new JwtUtil();
        Cookie[] cookieArr = request.getCookies();
        String token = jwt.tokenInCookie(cookieArr);

        if(tokenService.tokenIsExist(token)){//提交的token在黑名单
            token = "";
        }
        if(token.equals("")){//提交的cookie没有token

            HttpSession session = request.getSession();

            session.setAttribute("username",username);

        }else {
            if(jwt.verifyToken(token)){
                username = jwt.verify(token).getClaim("username").asString();
                Integer userId =  jwt.verify(token).getClaim("user_id").asInt();
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                session.setAttribute("user_id",userId);
            }
        }
        return true;
    }

}
