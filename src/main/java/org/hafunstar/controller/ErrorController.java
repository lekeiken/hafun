package org.hafunstar.controller;

import org.hafunstar.exception.ExceptionInfo;
import org.hafunstar.exception.SysExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
@RequestMapping("/error")
public class ErrorController {


    private SysExceptionResolver ex;
    @Autowired
    public void setEx(@Qualifier("exceptionResolver") SysExceptionResolver ex) {
        this.ex = ex;
    }

    @RequestMapping("/1")
    public ModelAndView errorOne(HttpServletRequest Request, HttpServletResponse Response, Object o, Exception e){


        return ex.resolveException(Request,Response, o,e);

    }
}
