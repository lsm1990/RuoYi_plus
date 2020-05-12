package com.ruoyi.third.baidu.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaiduApiResult;
import com.ruoyi.third.baidu.util.Base64Util;
import com.ruoyi.third.baidu.util.FileUtil;
import com.ruoyi.third.baidu.util.HttpUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  人脸识别
 */
public class FaceAPI extends BaiduAi {


    private final static String URL_FACE_DETECT = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
    private final static String URL_FACE_MATCH = "https://aip.baidubce.com/rest/2.0/face/v3/match";
    private final static String URL_FACE_SEARCH = "https://aip.baidubce.com/rest/2.0/face/v3/search";
    private final static String URL_FACE_IDCARD = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify";

    /**
     * API调用人脸检测
     * @param value   图片路径或图片库内的face_token
     * @param imgType 图片类型 IMG_TYPE.BASE64 是本地图片  IMG_TYPE.FACE_TOKEN 表示试用图片库内的face_token IMG_TYPE.URL表示图片网络路径
     * @return
     */
    public static String faceDetect(String value,IMG_TYPE imgType){

        Map<String, Object> map = new HashMap<String, Object>();
        if(imgType==IMG_TYPE.BASE64){
            byte[] bytes1 = new byte[0];
            try {
                bytes1 = FileUtil.readFileByBytes(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String image1 = Base64Util.encode(bytes1);
            map.put("image", image1);
        }else if(imgType==IMG_TYPE.FACE_TOKEN){
            map.put("image", value);
        }else{
            //网络路径
            map.put("image", value);
        }
        map.put("face_field", "age,beauty,expression,gender,glasses,race,qualities");
        map.put("image_type",imgType);

        String params = JSON.toJSONString(map);
        try {
            String result = HttpUtil.post(URL_FACE_DETECT,getAccessToken(), params);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //人脸对比方法
    private static BaiduApiResult faceMatchBase(Map<String, Object> img1, Map<String, Object> img2){
        try {

            /*byte[] bytes1 = util.FileUtil.readFileByBytes("【本地文件1地址】");
            byte[] bytes2 = util.FileUtil.readFileByBytes("【本地文件2地址】");
            String image1 = util.Base64Util.encode(bytes1);
            String image2 = util.Base64Util.encode(bytes2);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");*/

            List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
            images.add(img1);
            images.add(img2);
            String params = JSON.toJSONString(images);
            String result = HttpUtil.post(URL_FACE_MATCH, getAccessToken(), params);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片比对
     * @param v1 第一张图片 可能是路径也可能是face_token
     * @param imgType1 第一张图片图片类型
     * @param v2 第二张图片 可能是路径也可能是face_token
     * @param imgType2 第二张图片图片类型
     * @return
     */
    public static BaiduApiResult faceMatch(String v1,IMG_TYPE imgType1,String v2,IMG_TYPE imgType2){
        return faceMatchBase(getImgMapParam_Match(v1,imgType1),getImgMapParam_Match(v2,imgType2));
    }
    //拼装人脸比对图片参数
    private static Map<String, Object> getImgMapParam_Match(String value,IMG_TYPE imgType){
        Map<String, Object> map1 = new HashMap<String, Object>();
        if(imgType==IMG_TYPE.BASE64){
            byte[] bytes1 = new byte[0];
            try {
                bytes1 = FileUtil.readFileByBytes(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String image1 = Base64Util.encode(bytes1);
            map1.put("image", image1);
        }else if(imgType==IMG_TYPE.FACE_TOKEN){
            map1.put("image", value);
        }else{
            //网络路径
            map1.put("image", value);
        }
        map1.put("image_type", imgType);
        map1.put("face_type", FACE_TYPE.LIVE);//LIVE 表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等，IDCARD 表示身份证芯片照：二代身份证内置芯片中的人像照片，WATERMARK ：表示带水印证件照：一般为带水印的小图，如公安网小图 CERT：表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片默认LIVE
        map1.put("quality_control", QUALITY_CONTROL.NORMAL);
        map1.put("liveness_control",LIVENESS_CONTROL.NONE);
        return map1;
    }

    //人脸搜索
    public static BaiduApiResult faceSearch(String value,IMG_TYPE imgType){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if(imgType==IMG_TYPE.BASE64){
                byte[] bytes1 = new byte[0];
                try {
                    bytes1 = FileUtil.readFileByBytes(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String image1 = Base64Util.encode(bytes1);
                map.put("image", image1);
            }else if(imgType==IMG_TYPE.FACE_TOKEN){
                map.put("image", value);
            }else{
                //网络路径
                map.put("image", value);
            }
            map.put("image_type", imgType);
            BaiduApiResult baiduApiResult = FaceManagerAPI.getGroupList();
            if(!baiduApiResult.isSucceed()){
                map.put("group_id_list", FaceManagerAPI.GROUP_DEFAULT);
            }else{
                JSONObject json=baiduApiResult.get("result");
                //JSONArray  array= JSONArray.parseArray(res.get("group_id_list").toString());
                List<String> list= JSONArray.parseArray(json.get("group_id_list").toString(), String.class);
                if(list!=null&list.size()>0){
                    String group_id_list="";
                    for(String s:list){
                        group_id_list+=s+",";
                    }
                    if(group_id_list.endsWith(",")){
                        group_id_list=group_id_list.substring(0,group_id_list.length()-1);
                        map.put("group_id_list", group_id_list);
                    }
                }
            }

            map.put("quality_control",QUALITY_CONTROL.LOW);
            map.put("liveness_control", LIVENESS_CONTROL.NONE);//活体检测控制
            String params = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_FACE_SEARCH, getAccessToken(), params);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //身份证人脸验证
    public static BaiduApiResult personverify(String value,IMG_TYPE imgType,String ID,String name) {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if(imgType==IMG_TYPE.BASE64){
                byte[] bytes1 = new byte[0];
                try {
                    bytes1 = FileUtil.readFileByBytes(value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String image1 = Base64Util.encode(bytes1);
                map.put("image", image1);
            }else if(imgType==IMG_TYPE.FACE_TOKEN){
                map.put("image", value);
            }else{
                //网络路径
                map.put("image", value);
            }
            map.put("image_type", imgType);

            map.put("id_card_number", ID);
            map.put("liveness_control",LIVENESS_CONTROL.NONE);
            map.put("name", name);
            map.put("quality_control", QUALITY_CONTROL.LOW);
            String params = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_FACE_IDCARD, getAccessToken(), params);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args)  {
       String Filepath = "C:\\Users\\Administrator\\Desktop\\111.jpg";
        String face_token1 = "a7612ed974a2c5c11f44a9681706fe3e";
         String s=  faceDetect(Filepath,IMG_TYPE.BASE64);
        System.out.println(s);
     //   bean.BaiduApiResult baiduApiResult =  faceDetect(face_token1,IMG_TYPE.FACE_TOKEN);

       /* String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528821260802&di=c3fa7991da1e87524587327dcd402449&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fb7003af33a87e9508dc95d791b385343faf2b4cd.jpg";
        bean.BaiduApiResult baiduApiResult = detect_url_img(url1);*/


        String face_token = "b55afddac67812e45b2b7bc22c2a5d22";
      //  bean.BaiduApiResult baiduApiResult = detect_face_token(face_token);
        //  bean.BaiduApiResult baiduApiResult =  faceMatch(getImgMapParam_Match("C:\\Users\\Administrator\\Desktop\\22.jpg", IMG_TYPE.BASE64),getImgMapParam_Match("C:\\Users\\Administrator\\Desktop\\22.jpg", IMG_TYPE.BASE64));
      // bean.BaiduApiResult baiduApiResult =faceSearch(face_token,IMG_TYPE.FACE_TOKEN);


       // bean.BaiduApiResult baiduApiResult = personverify(Filepath,IMG_TYPE.BASE64,"3715211987072052971","武继跃");
       /* if(baiduApiResult.isSucceed()){
            System.out.println("成功:"+baiduApiResult);
        }else{
            String res= baiduApiResult.getErrorMsg()+"错误代码:"+baiduApiResult.getErrorCode();
            System.out.println(res);
        }*/
    }
}
