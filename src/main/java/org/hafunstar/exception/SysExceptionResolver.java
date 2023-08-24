package org.hafunstar.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */
@Component
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ExceptionInfo ei = null;
        if(e instanceof ExceptionInfo){

            ei = (ExceptionInfo) e;
        }else{
            ei = new ExceptionInfo("未知异常,系统错误！");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",ei.getMessage());
        mv.setViewName("error/error");
        e.printStackTrace();
        return mv;
    }
}
