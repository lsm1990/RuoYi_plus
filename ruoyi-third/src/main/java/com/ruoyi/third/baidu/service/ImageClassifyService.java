package com.ruoyi.third.baidu.service;


import com.ruoyi.third.baidu.api.ImageClassifyAPI;
import com.ruoyi.third.baidu.bean.imgClassify.*;
import com.ruoyi.third.baidu.util.ImageUtil;

/**
 * @author wujiyue
 */
public class ImageClassifyService {
    public static void main(String[] args){
       /* String path="C:\\Users\\wjy\\Desktop\\dish.jpg";
        DishResult result = dish(path);
        System.out.println(result);*/

        /*String path="C:\\Users\\wjy\\Desktop\\logo.jpg";
        LogoResult result = logo(path);
        System.out.println(result);*/

        /*String path="C:\\Users\\wjy\\Desktop\\plant.jpg";
        PlantResult result = plant(path);
        System.out.println(result);*/

        /*String path="C:\\Users\\wjy\\Desktop\\car.jpg";
        AnimalResult result = animal(path);
        System.out.println(result);*/

        String path="C:\\Users\\wjy\\Desktop\\timg.jpg";
        ObjectDetectResult result = object_detect(path);
        System.out.println(result);
       String b= ImageUtil.paintRectangle(path, result.getLocation());
        System.out.println(b);
    }


    public static DishResult dish(String path){
        String json= ImageClassifyAPI.dish(path, 3);
        return DishResult.create(json);
    }
    public static CarResult car(String path){
        String json= ImageClassifyAPI.car(path, 3);
        return CarResult.create(json);
    }
    public static LogoResult logo(String path){
        String json= ImageClassifyAPI.logo(path, false);
        return LogoResult.create(json);
    }
    public static PlantResult plant(String path){
        String json= ImageClassifyAPI.plant(path);
        return PlantResult.create(json);
    }
    public static AnimalResult animal(String path){
        String json= ImageClassifyAPI.animal(path, 3);
        return AnimalResult.create(json);
    }
    //主体对象检测
    public static ObjectDetectResult object_detect(String path){
        String json= ImageClassifyAPI.object_detect(path, false);
        return ObjectDetectResult.create(json);
    }

}
