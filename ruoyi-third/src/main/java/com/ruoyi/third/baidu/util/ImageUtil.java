package com.ruoyi.third.baidu.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

/**
 * @author wujiyue
 */
public class ImageUtil {
    public static void main(String[] args) throws IOException {

    }
    public static String  paintRectangle(String path,Map location) {
        try {
            String sufix = path.substring(path.lastIndexOf(".") + 1);
            String path_res = path.substring(0, path.lastIndexOf(".")) + "_temp" + "." + sufix;
            int x = 0;
            int y = 0;
            int width = 0;
            int height = 0;
            if (location != null) {
                x = Integer.valueOf(String.valueOf(location.get("left")));
                y = Integer.valueOf(String.valueOf(location.get("top")));
                width = Integer.valueOf(String.valueOf(location.get("width")));
                height = Integer.valueOf(String.valueOf(location.get("height")));
            }
            //图片路径
            InputStream in = new FileInputStream(path);
            BufferedImage image = ImageIO.read(new File(path));
            Graphics g = image.getGraphics();
            //画笔颜色
            g.setColor(Color.RED);
            //矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            g.drawRect(x, y, width, height);
            //g.dispose();
            //输出图片的地址
            FileOutputStream out = new FileOutputStream(path_res);
            ImageIO.write(image, sufix, out);
            return path_res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 覆盖原来图片
     * @param path
     * @param location
     * @return
     */
    public static boolean  paintRectangle2(String path,Map location){
        try{
            String sufix=path.substring(path.lastIndexOf(".")+1);
            String path_res=path.substring(0,path.lastIndexOf("."))+"_temp"+"."+sufix;
            Double roll=0d;
            int x=0;int y=0;int width=0;int height=0;
            if(location!=null){
               Double t_x=Double.valueOf(String.valueOf(location.get("left")));
               Double t_y=Double.valueOf(String.valueOf(location.get("top")));
                x= Integer.parseInt(new java.text.DecimalFormat("0").format(t_x));
                y= Integer.parseInt(new java.text.DecimalFormat("0").format(t_y));
                width=Integer.valueOf(String.valueOf(location.get("width")));
                height=Integer.valueOf(String.valueOf(location.get("height")));
                roll=Double.valueOf(String.valueOf(location.get("roll")));
            }
            //图片路径
            InputStream in = new FileInputStream(path);
            BufferedImage image = ImageIO.read(new File(path));
            //Graphics g = image.getGraphics();
            Graphics2D g = (Graphics2D)image.getGraphics();
            //画笔颜色
            g.setColor(Color.RED);
            //矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            g.drawRect(x, y, width, height);
           // g.transform(Math.toRadians(45),x+width/2,y+height/2);
            // g.dispose();
            //输出图片的地址
            FileOutputStream out = new FileOutputStream(path);
            ImageIO.write(image, sufix, out);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
