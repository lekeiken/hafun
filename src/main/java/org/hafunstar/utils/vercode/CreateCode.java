package org.hafunstar.utils.vercode;

import java.awt.*;
import java.awt.image.BufferedImage;
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

public class CreateCode {

    private BufferedImage image;

    private String code;

    private static String strPool= "abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY3456789";
    private String[] ft = { "Arial", "Verdana", "Comic Sans MS", "Impact", "Haettenschweiler", "Lucida Sans Unicode", "Garamond", "Courier New", "Book Antiqua", "Arial Narrow" };

    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    Random random = new Random();

    public CreateCode() {
        init();// 初始化属性
    }

    /*
     * 取得RandomNumUtil实例
     */
    public static CreateCode Instance() {
        return new CreateCode();
    }

    /*
     * 取得验证码图片
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /*
     * 取得图片的验证码
     */
    public String getString() {
        return code.toLowerCase();
    }

    private void init(){
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        //设置背景色
        setBackGround(g);
        //4.设置图片的边框
        setBorder(g);
        //5.在图片上画干扰线
        drawRandomLine(g);

        this.code = createRandomChar((Graphics2D) g,strPool);
        //图像生成
        g.dispose();

        //赋值图像
        this.image = image;


    }

    private void setBackGround(Graphics g) {
        g.setColor(Color.WHITE);
        // 填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void setBorder(Graphics g) {
        g.setColor(Color.BLUE);
        // 边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /**
     * 画线条
     * @param g
     */
    private void drawRandomLine(Graphics g) {
        g.setColor(getRandColor(100,250));
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 随机生成字符画入并返回
     * @param g
     * @param baseChar
     * @return
     */
    private String createRandomChar(Graphics2D g,String baseChar){
        g.setFont(new Font(ft[random.nextInt(ft.length)], Font.BOLD, 20));
        StringBuffer sb = new StringBuffer();
        int x = 5;
        String ch ="";
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 设置字体旋转角度
            int degree = random.nextInt() % 30;
            ch = baseChar.charAt(random.nextInt(baseChar.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }

    /**
     * 生成随机的颜色
     * @param fc 最小值 1以上
     * @param bc 最大值  254 以下
     * @return
     */
    private Color getRandColor(int fc, int bc) {

        int r = 0;
        int g = 0;
        int b = 0;
        if(fc > 0 && bc < 255){

            r = fc + random.nextInt(bc - fc);
            g = fc + random.nextInt(bc - fc);
            b = fc + random.nextInt(bc - fc);
        }
        return new Color(r, g, b);
    }




}
