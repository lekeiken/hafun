package org.hafunstar.controller.admin;

import org.hafunstar.domain.Account;
import org.hafunstar.domain.ResponseData;
import org.hafunstar.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UploadImgController {

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    /**
     * 从网站后台上传图片
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/manager/upload",method = RequestMethod.POST)
    public Map<String, Object> adminUpload(HttpServletRequest request,@RequestParam("file") MultipartFile upload) {

        ResponseData data = new ResponseData();
        if(!upload.isEmpty()){

            try{
                //先获得上传的位置

                String pathTemp = request.getSession().getServletContext().getRealPath("uploads/temp");
                //目录不存在就创建

                File file1 = new File(pathTemp);

                if(!file1.exists()){
                    file1.mkdirs();
                }
                String filename = upload.getOriginalFilename();
                //获取文件后缀名 lastIndexOf返回.之前的字符数，substring去掉这些字符数，返回新的只有后缀的字符串
                String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
                if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")){
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String d = sdf.format(date);
                    String metafile = d+suffix;
                    //后台上传的文件先放在临时文件夹下
                    upload.transferTo(new File(pathTemp,metafile));
                    data.putDataValue("info","上传成功");

                }else {
                    data.putDataValue("info","上传格式错误，请重新上传");
                }
                //随机生成唯一id

            }catch (IOException e){

                data.putDataValue("info","上传失败");
                e.printStackTrace();
            }

        }else {
            data.putDataValue("info","上传失败,因为文件是空的。");
        }

        return data.getData();
    }

    /**
     * 用户上传头像图片
     * @param request
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/upload_img",method = RequestMethod.POST)
    public Map<String, Object> userUpload(Account a, HttpServletRequest request, @RequestParam("file") MultipartFile file){

        ResponseData data = new ResponseData();
        String accountName = (String) request.getSession().getAttribute("username");//邮箱或电话
        if(!file.isEmpty()){

            try{
                //先获得上传的位置
                String path = request.getSession().getServletContext().getRealPath("uploads/user/");
                //目录不存在就创建
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                String filename = file.getOriginalFilename();
                //获取文件后缀名 lastIndexOf返回.之前的字符数，substring去掉这些字符数，返回新的只有后缀的字符串
                String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
                if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")){
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String d = sdf.format(date);
                    String metafile = accountName +"-"+ d +suffix;
                    file.transferTo(new File(path,metafile));
                    data.putDataValue("img_url","/uploads/user/"+metafile);
                    data.putDataValue("info","上传成功");
                    //修改数据库用户头像图片地址
                    a.setAccountEmail(accountName);
                    a.setAccountImgUrl((String) data.getData("img_url"));
                    accountService.accountUpdateImgUrl(a);

                }else {
                    data.putDataValue("info","上传格式错误，请重新上传");
                }

            }catch (IOException e){

                data.putDataValue("info","上传失败");
                e.printStackTrace();
            }

        }else {
            data.putDataValue("info","上传失败,因为文件是空的。");
        }

        return data.getData();
    }

}
