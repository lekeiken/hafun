package org.hafunstar.controller;

import org.hafunstar.domain.Captcha;
import org.hafunstar.utils.vercode.CaptchaUtil;
import org.hafunstar.utils.vercode.CreateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
public class ImageController {

    /**
     * 响应管理后台验证码图片
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/image")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置浏览器不缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //将验证码存入session
        CreateCode createCode = CreateCode.Instance();
        HttpSession session = request.getSession(true);
        session.setAttribute("verificationCode",createCode.getString());
        //将图片使用流传给浏览器
        BufferedImage image = createCode.getImage();
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        //关闭流
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 用户登录验证码
     * @param request
     * @param mv
     * @return
     * @throws IOException
     */
    @RequestMapping("/userCapt")
    public ModelAndView userLoginCapt(HttpSession session, ModelAndView mv) throws IOException {

        //生成一个验证码
        CaptchaUtil captchaUtil = new CaptchaUtil();
        String url ="D:\\EditorAndDevelop\\img";
        BufferedImage img = captchaUtil.getImage(url);

        String [] chars = captchaUtil.getChineseCharacterStr();//生成的汉字
        List<Captcha> point = captchaUtil.getCaptchas();//汉字坐标

        session.setAttribute("point",point);//储存进session
        ByteArrayOutputStream out = new ByteArrayOutputStream();//将图片转换成byte数组
        try {
            ImageIO.write(img,"jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imgBin = out.toByteArray();

        BASE64Encoder encoder = new BASE64Encoder();//对字节数组base64编码

        String data = encoder.encode(imgBin);

        String dataurl = "data:image/jpeg;base64," + data.replace("\r\n","");

        mv.addObject("char",chars);
        mv.addObject("img_data",dataurl);
        mv.setViewName("user_captcha");
        return mv;
    }
}
