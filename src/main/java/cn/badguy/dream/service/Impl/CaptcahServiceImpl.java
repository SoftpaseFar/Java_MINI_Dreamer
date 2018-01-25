package cn.badguy.dream.service.Impl;

import cn.badguy.dream.service.ICaptcahService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


@Service("iCaptcahService")
public class CaptcahServiceImpl implements ICaptcahService {

    public String getCaptcah(HttpServletResponse response) {
        //定义验证码的宽度和高度
        int width = 120, height = 30;
        //在内存中创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建图片的上下文
        Graphics2D g = image.createGraphics();
        //产生随机对象,此随机对象主要用于算术表达式的数字
        Random random = new Random();
        //设置背景
        g.setColor(getRandomColor(240, 250));
        //设置字体
        g.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        //开始绘制
        g.fillRect(0, 0, width, height);

        //干扰线的绘制，绘制线条到图片中
        g.setColor(getRandomColor(180, 230));
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            g.drawLine(x, y, x1, y1);
        }

        //开始进行对算术验证码表达式的拼接
        int num1 = (int) (Math.random() * 10 + 1);
        int num2 = (int) (Math.random() * 10 + 1);
        int fuhao = random.nextInt(3);//产生一个[0,2]之间的随机整数
        //记录符号
        String fuhaostr = null;
        int result = 0;
        switch (fuhao) {
            case 0:
                fuhaostr = "+";
                result = num1 + num2;
                break;
            case 1:
                fuhaostr = "-";
                result = num1 - num2;
                break;
            case 2:
                fuhaostr = "*";
                result = num1 * num2;
                break;
            //case 3 : fuhaostr = "/";result = num1 / num2;break;
        }
        //拼接算术表达式,用户图片显示。
        String calc = num1 + " " + fuhaostr + " " + num2 + " = ?";
        //设置随机颜色
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        //绘制表达式
        g.drawString(calc, 5, 25);
        //结束绘制
        g.dispose();
        try {
            //输出图片到页面
            ImageIO.write(image, "JPEG", response.getOutputStream());
            System.out.println(String.valueOf(result));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return "";
    }

    private Color getRandomColor(int fc, int bc) {
        //利用随机数
        Random random = new Random();
        //随机颜色,了解颜色-Color(red,green,blue).rgb三元色 0-255
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }



}
