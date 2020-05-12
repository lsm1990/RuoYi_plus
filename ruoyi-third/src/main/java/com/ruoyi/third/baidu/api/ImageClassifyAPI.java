package com.ruoyi.third.baidu.api;



import com.ruoyi.third.baidu.util.Base64Util;
import com.ruoyi.third.baidu.util.FileUtil;
import com.ruoyi.third.baidu.util.HttpUtil;

import java.net.URLEncoder;

/**
 * 图像识别
 * @author wujiyue
 */
public class ImageClassifyAPI extends BaiduAi{

    private static final String URL_advanced_general="https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
    private static final String URL_object_detect="https://aip.baidubce.com/rest/2.0/image-classify/v1/object_detect";

    private static final String URL_dish="https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";

    private static final String URL_car="https://aip.baidubce.com/rest/2.0/image-classify/v1/car";
    private static final String URL_logo="https://aip.baidubce.com/rest/2.0/image-classify/v2/logo";
    private static final String URL_plant="https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
    private static final String URL_animal="https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
    //该请求用于通用物体及场景识别，即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片中的多个物体及场景标签。
    /**
     * {
     "result": [
     {
     "score": 0.996184,
     "root": "商品-电脑办公",
     "keyword": "台式机"
     },
     {
     "score": 0.799921,
     "root": "Logo",
     "keyword": "联想"
     },
     {
     "score": 0.424538,
     "root": "商品-电脑办公",
     "keyword": "电脑"
     },
     {
     "score": 0.213236,
     "root": "商品-电脑办公",
     "keyword": "服务器/工作站"
     },
     {
     "score": 0.10919,
     "root": "商品-数码产品",
     "keyword": "台式电脑"
     }
     ],
     "result_num": 5,
     "log_id": 2689001477
     }
     * @param path
     * @return
     */
    public static String advanced_general(String path) {

        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_advanced_general, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //用户向服务请求检测图像中的主体位置。
    public static String object_detect(String path,boolean withFace){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            if(withFace){
                params+="&with_face=1";
            }else{
                params+="&with_face=0";
            }
            String result = HttpUtil.post(URL_object_detect, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    //该请求用于菜品识别。即对于输入的一张图片（可正常解码，且长宽比适宜），输出图片的菜品名称、卡路里信息、置信度。
    /*{
        "log_id": 2471272194,
            "result_num": 5,
            "result": [
        {
            "name": "烧烤（串类）",
                "calorie": 333.33,
                "probability": 0.35874313116074
        },
        {
            "name": "鱿鱼",
                "calorie": 333.33,
                "probability": 0.20610593259335
        },
        {
            "name": "板筋",
                "calorie": 333.33,
                "probability": 0.15860831737518
        },
        {
            "name": "鸡脆骨",
                "calorie": 333.33,
                "probability": 0.077698558568954
        },
        {
            "name": "麻辣烫",
                "calorie": 333.33,
                "probability": 0.041968926787376
        }
        ]
    }
   识别失败： {"log_id": 2677811678621716503, "result_num": 1, "result": [{"calorie": "-1", "has_calorie": true, "name": "非菜", "probability": "0.202248"}]}
    */
    public static String dish(String path,Integer topNum){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8")+"&filter_threshold=0.95";
            if(topNum!=null&&topNum>0){
                params+="&top_num="+topNum;
            }else{
                params+="&top_num="+5;
            }
            String result = HttpUtil.post(URL_dish, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //车型识别

    /**
     *
     * {"log_id": 6322863901722339584, "location_result": {"width": 729, "top": 193, "height": 302, "left": 132}, "result": [{"score": 0.99944090843201, "name": "宝骏560", "year": "2015-2017"}, {"score": 0.00022018732852302, "name": "宝骏730", "year": "2016-2017"}, {"score": 0.00013495099847205, "name": "东风风行风行SX6", "year": "2016"}, {"score": 1.4159925740387e-05, "name": "西雅特Ateca", "year": "无年份信息"}, {"score": 1.1880139027198e-05, "name": "东风风度MX5", "year": "2016-2017"}, {"score": 1.1864442967635e-05, "name": "大众途昂", "year": "2017"}, {"score": 9.8557611636352e-06, "name": "本田Pilot", "year": "无年份信息"}, {"score": 9.3120852397988e-06, "name": "开瑞K60", "year": "2017"}, {"score": 9.2856907940586e-06, "name": "标致3008", "year": "2013-2016"}, {"score": 8.5839219536865e-06, "name": "双龙途凌", "year": "2016-2017"}, {"score": 6.7547152866609e-06, "name": "江淮瑞风S系列", "year": "2017"}, {"score": 6.1060736697982e-06, "name": "北汽幻速幻速S6", "year": "2016-2017"}], "color_result": "白色"}
     * @param path
     * @param topNum
     * @return
     */
    public static String car(String path,Integer topNum) {
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            if(topNum!=null&&topNum>0){
                param+="&top_num="+topNum;
            }else{
                param+="&top_num="+5;
            }
            String result = HttpUtil.post(URL_car, getAccessToken(), param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * logo检测
     * @param path
     * @param flag 是否只检索用户子库，true则只检索用户子库，false(默认)为检索底库+用户子库
     * @return
     *{"log_id": 3510281299570976969, "result_num": 1, "result": [{"type": 0, "name": "香奈儿", "probability": 1, "location": {"width": 288, "top": 35, "left": 164, "height": 224}}]}
     */
    public static String logo(String path,Boolean flag) {
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            if(flag!=null && flag){
                param += "&custom_lib=true";
            }else{
                param += "&custom_lib=false";
            }
            String result = HttpUtil.post(URL_logo, getAccessToken(), param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param path
     * @return
     * {"log_id": 8348105070592846270, "result": [{"score": 0.83211559057236, "name": "黑心金光菊"}, {"score": 0.10564804077148, "name": "金光菊"}, {"score": 0.025003267452121, "name": "连翘"}, {"score": 0.0059820651076734, "name": "虎眼金光菊"}, {"score": 0.0052708736620843, "name": "狗舌草"}]}
     */
    public static String plant(String path) {
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            String result = HttpUtil.post(URL_plant, getAccessToken(), param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 动物识别
     * @param path
     * @param top_num 返回预测得分top结果数，默认为6
     * @return
     * {"log_id": 8619617754141201963, "result": [{"score": "0.97435", "name": "红鹳"}, {"score": "0.011475", "name": "红鹤"}]}
     */
    public static String animal(String path,Integer top_num) {
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            if(top_num!=null&&top_num > 0){
                param +="&top_num="+top_num;
            }else{
                param +="&top_num=6";
            }
            String result = HttpUtil.post(URL_animal, getAccessToken(), param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        /*String path="C:\\Users\\Administrator\\Desktop\\ge2.jpg";
        String s=advanced_general(path);
        System.out.println(s);*/
        /*String path="C:\\Users\\Administrator\\Desktop\\lc.jpg";
        String s=dish(path,3);
        System.out.println(s);*/

        /*String path="C:\\Users\\wjy\\Desktop\\dish.jpg";
        String s=car(path,2);
        System.out.println(s);*/

       /* String path="C:\\Users\\wjy\\Desktop\\car.jpg";
        String s= logo(path, false);
        System.out.println(s);*/

        /*String path="C:\\Users\\wjy\\Desktop\\plant.jpg";
        String s= plant(path);
        System.out.println(s);*/
        String path = "C:\\Users\\wjy\\Desktop\\car.jpg";
        String s = object_detect(path,false);
        System.out.println(s);

    }
}
