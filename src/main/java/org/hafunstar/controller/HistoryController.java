package org.hafunstar.controller;

import org.hafunstar.domain.History;
import org.hafunstar.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
public class HistoryController {

    private HistoryService historyService;
    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    @RequestMapping(value = "/History")
    public ModelAndView hisPage(ModelAndView mv){

        List<History> histories = historyService.findHistoryAll();
        mv.addObject("historyList",histories);
        mv.setViewName("before");
        return mv;
    }
}
