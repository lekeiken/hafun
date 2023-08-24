package org.hafunstar.utils.vercode;

import org.apache.ibatis.jdbc.Null;
import org.hafunstar.domain.Captcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @PackgeName:
 * @ClassName:
 * @Author:
 * @Date:
 * @project name:
 * @Version:
 * @Description:
 */

public class CaptchaUtil {
    private static int imgWidth = 300;
    private static int imgHeight = 180;
    private static int charNum = 3;
    private static int fontSize = 32;
    //汉字的宽度和长度
    private static int fontImgWidth = 30;
    private static int fontImgHeight = 30;

    String [] chineseCharacterStr;
    //坐标
    List<Captcha> captchaList = new ArrayList<>();

    public CaptchaUtil() {
        setChars();
        repeatCheck();
    }

    public String[] getChineseCharacterStr() {
        return chineseCharacterStr;
    }

    public List<Captcha> getCaptchas() {
        return captchaList;
    }

    public BufferedImage getImage(String url) throws IOException {

        //给一个图片路径随机获取一个图片当背景
        BufferedImage imgCanvas = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_BGR);

        BufferedImage backGroundImage = (BufferedImage) ImageIO.read(backGroundImage(url));

        Graphics graphics = imgCanvas.getGraphics();
        graphics.drawImage(backGroundImage,0,0,imgWidth, imgHeight,null);

        graphics.setFont(new Font("楷体", Font.BOLD, fontSize));
        List<Captcha> cList = getCaptchas();

        String [] str = getChineseCharacterStr();
        for(int i = 0;i<charNum;i++){
            if(cList.size() > 1){
                Captcha captcha = cList.get(i);
                graphics.drawImage(getCharImg(getRandomColor(),str[i]),captcha.getX(),captcha.getY(),fontImgWidth,fontImgHeight,null);
                graphics.setColor(getRandomColor());
            }
        }
        return imgCanvas;
    }

    /**
     * 获取文字图片
     * @param fontColor
     * @param charList
     * @return
     * @throws UnsupportedEncodingException
     */
    private static BufferedImage getCharImg(Color fontColor,String charStr) throws UnsupportedEncodingException {
        BufferedImage fillRect = new BufferedImage(fontImgWidth, fontImgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = fillRect.createGraphics();
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0,fontImgWidth,fontImgHeight);
        g.setColor(fontColor);
        Font font = new Font("微软雅黑", Font.BOLD, fontSize);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(30),0,0);
        font.deriveFont(affineTransform);
        g.setFont(font);
        g.drawString(charStr,0,fontImgHeight-5);
        g.dispose();
        return fillRect;
    }

    /**
     * 检验第2 3生成的坐标和第一个是否重复
     * @param captcha
     * @return
     */
    public void repeatCheck(){
        List<Captcha> captchas = new ArrayList<>();
        captchas.add(getPoint());

        int a = 1;
        Boolean b = null;
        while (a != charNum){
            Captcha captcha = getPoint();
            //新生成的坐标
            int maxX =captcha.getX()+fontImgWidth;
            int minX =captcha.getX()-fontImgWidth;
            int maxY =captcha.getY()+fontImgHeight;
            int minY =captcha.getY()-fontImgHeight;
            //遍历已产生的坐标，如果重复重新获取对比
            for(int i = 0;i<captchas.size();i++){
                int x1 =captchas.get(i).getX();
                int y1 =captchas.get(i).getY();
                if(x1>maxX || x1<minX && y1>maxY || y1<minY){
                    b = true;
                }else {
                    b = false;
                    break;
                }
            }
            //如果可用就添加到list里 a条件++
            if(b == true){
                captchas.add(captcha);
                a++;
            }
        };

           this.captchaList = captchas;
    }

    /**
     * 验证前端用户传的验证码和本地服务器存的验证码是否一致
     * @param captchas1用户
     * @param captchas2服务器
     * @return
     */
    public Boolean checkPoint(List<Captcha> catches1, List<Captcha> catches2){

        Boolean b = null;
        int i = 0;
        try{

            for(Captcha captcha : catches2){

                //得到正确的坐标范围
                int maxX =captcha.getX()+fontImgWidth;
                int minX =captcha.getX();
                int maxY =captcha.getY()+fontImgHeight;
                int minY =captcha.getY();
                //获得相同下标的用户传来的字坐标
                Captcha c = catches1.get(i);

                if(c.getX()<maxX && c.getX()>minX && c.getY()<maxY && c.getY()>minY){
                    b = true;

                }else {
                    b = false;
                    //只要有不通过的就break
                    break;
                }

                i++;

            }
        }catch (NullPointerException e){
            return false;
        }

        return b;

    }

    /**
     * 获取坐标
     * @return
     */
    public Captcha getPoint(){
        Random random = new Random();
        int x=random.nextInt(imgWidth-(fontImgWidth+fontImgWidth))+fontImgHeight;
        int y=random.nextInt(imgHeight-(fontImgHeight+fontImgHeight))+fontImgHeight;
        Captcha captcha = new Captcha();
        captcha.setX(x);
        captcha.setY(y);
        return captcha;
    }

    /**
     * 生成一个汉字数组
     * @return
     */
    public void setChars(){
        String [] strAerry= new String[charNum];
        for(int i =0;i<charNum;i++){
            strAerry[i] = getChineseCharacters();
        }
        this.chineseCharacterStr = strAerry;
    }

    /**
     * 在传入的文件目录下随机获取一个图片当背景
     * @param dir
     * @return
     */
    private static File backGroundImage(String dir){
        File file = new File(dir);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            Random random = new Random();
            int i = random.nextInt(files.length);
            return files[i];
        }else{
            return file;
        }
    }

    /**
     * 随机获取颜色
     * @return
     */
    private static Color getRandomColor(){
        Random random = new Random();
        return new Color(
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));

    }

    /**
     * 随机产生一个汉字
     * @return
     */
    private static String getChineseCharacters() {
        Random random = new Random();
        Integer hightPos = (176 + Math.abs(random.nextInt(39)));
        Integer lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            return  new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }

    }


}
