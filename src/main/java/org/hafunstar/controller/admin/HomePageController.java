package org.hafunstar.controller.admin;

import org.hafunstar.domain.HomePage;
import org.hafunstar.domain.ResponseData;
import org.hafunstar.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/manager")
public class HomePageController {

    private HomePageService homePageService;
    @Autowired
    public void setHomePageService(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    /**
     * 保存一个新编辑的内容
     * @param hp
     * @return
     */
    @RequestMapping(value = "/SaveHP",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveHomePage(@RequestBody HomePage hp){
        ResponseData data = new ResponseData();
        int i = homePageService.saveHomePageInfo(hp);
        hp.setPageIsNew(0);//修补可以任意修改内容状态漏洞
        data.putDataValue("info",i>0? "保存成功":"保存失败");
        return data.getData();
    }

    /**
     * 保存一个草稿，只能保存一个，保存新的草稿会删除之前的
     * @param hp
     * @return
     */
    @RequestMapping(value = "/SaveDraft",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveHomePageDraft(@RequestBody HomePage hp){
        ResponseData data = new ResponseData();
        //先删除之前的存草稿
        homePageService.deleteHomePageByInt(1);
        hp.setPageIsNew(1);
        int i = homePageService.saveHomePageDraft(hp);
        data.putDataValue("info",i>0? "保存成功":"保存失败");
        return data.getData();
    }

    @RequestMapping(value = "/Draft",method = RequestMethod.GET)
    public @ResponseBody HomePage draftLoading(){
        HomePage hp = homePageService.findHomePageByNew(1);
        return hp;
    }

    @RequestMapping(value = "/SelectImg",method = RequestMethod.GET)
    public ModelAndView selectImg(ModelAndView mv){
        String filepath = "D:\\EditorAndDevelop\\Tomcat 8.5\\webapps\\game_recommend_war\\uploads\\temp";

        File file = new File(filepath);

        List<String> l = new ArrayList();
        if(file.isDirectory()){

            String [] s = file.list();
            for (int i = 0; i < s.length; i++) {
                File f = new File(file + "\\" + s[i]);
                if (f.isDirectory()) {
                } else {

                    l.add(s[i]);
                }
            }
        }
        mv.addObject("img_list",l);
        mv.setViewName("admin/all_img");
        return mv;
    }
    @RequestMapping(value = "/HomePageDel",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> homePageDel(@RequestParam("hid") String id){
        ResponseData data = new ResponseData();
        int i = homePageService.deleteHomePageById(Integer.parseInt(id));
        data.putDataValue("info",i>0? "删除成功":"删除失败");
        return data.getData();
    }
    @RequestMapping(value = "/GetH",method = RequestMethod.GET)
    public @ResponseBody HomePage homePageLoading(@RequestParam("hid") String id){
        HomePage hp = homePageService.findHomePageById(Integer.parseInt(id));
        return hp;
    }
    @RequestMapping(value = "/changePh",method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> changeHomePage(@RequestBody HomePage hp){
        ResponseData data = new ResponseData();
        //先删除之前的存草稿
        int i = homePageService.changeHomePage(hp);
        data.putDataValue("info",i>0? "修改成功":"修改失败");
        return data.getData();
    }
}
