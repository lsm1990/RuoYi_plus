package com.ruoyi.third.baidu.api;


import com.alibaba.fastjson.JSON;
import com.ruoyi.third.baidu.util.Base64Util;
import com.ruoyi.third.baidu.util.FileUtil;
import com.ruoyi.third.baidu.util.HttpUtil;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图像审核
 * @author wujiyue
 */
public class ImageVerifyAPI extends BaiduAi {

    private final static String URL_user_defined = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
    private final static String URL_face_audit = "https://aip.baidubce.com/rest/2.0/solution/v1/face_audit";
    private final static String URL_img_censor="https://aip.baidubce.com/api/v1/solution/direct/img_censor";

    /**
     *为用户提供色情识别、暴恐识别、政治敏感人物识别、广告识别、图像垃圾文本识别（反作弊）、恶心图像识别等一系列图像识别接口的一站式服务调用，并且支持用户在控制台中自定义配置所有接口的报警阈值和疑似区间，上传自定义文本黑库和敏感人物名单等。相比于组合服务接口，本接口除了支持自定义配置外，还对返回结果进行了总体的包装，按照用户在控制台中配置的规则直接返回是否合规，如果不合规则指出具体不合规的内容。
     * @param path      图片路径  本地路径或网络路径
     * @param imgType  图片类型  本地图片或网络图片
     * @return
     */
    public static String userDefined(String path,IMG_TYPE imgType) {

        try {
            String param ="";
            if(imgType != null && imgType==IMG_TYPE.URL){
                param = "imgUrl=" + path;
            }else{
                // 本地文件路径
                byte[] imgData = FileUtil.readFileByBytes(path);
                String imgStr = Base64Util.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                param = "image=" + imgParam;
            }
            String result = HttpUtil.post(URL_user_defined, getAccessToken(), param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用户头像审核
     * @param path  图片路径  本地路径或网络路径
     * @param imgType 图片类型  本地图片或网络图片
     * @return
     */
    public static String faceAudit(String path,IMG_TYPE imgType) {

        try {
            String param ="";
            if(imgType != null && imgType==IMG_TYPE.URL){
                param = "imgUrls=" + path;
            }else{
                byte[] imgData = FileUtil.readFileByBytes(path);
                String imgStr = Base64Util.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                param = "images=" + imgParam;
            }

            String result = HttpUtil.post(URL_face_audit, getAccessToken(), param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String img_censor(String path,IMG_TYPE imgType,Map<String, Object> sceneConf,IMG_CENSOR_SCENES... sceneses){
        try {
            Map<String, Object> input = new HashMap<String, Object>();

            if(imgType != null && imgType==IMG_TYPE.URL){
                input.put("imgUrl", path);
            }else{
                byte[] imgData = FileUtil.readFileByBytes(path);
                String imgStr = Base64Util.encode(imgData);
                input.put("image", imgStr);
            }
           /* Map<String, Object> sceneConf = new HashMap<String, Object>();
            Map<String, Object> ocrConf = new HashMap<String, Object>();
            ocrConf.put("recognize_granularity", "big");
            ocrConf.put("language_type", "CHN_ENG");
            ocrConf.put("detect_direction", true);
            ocrConf.put("detect_language", true);
            sceneConf.put("ocr", ocrConf);
            */
            List<String> scenes = new ArrayList<String>();
            if( sceneses != null && sceneses.length > 0){
                for(int i=0;i<sceneses.length;i++){
                    scenes.add(sceneses[i].toString());
                }
            }else{
                scenes.add("ocr");
                scenes.add("face");
                scenes.add("public");
                scenes.add("politician");
                scenes.add("antiporn");
                scenes.add("terror");
                scenes.add("webimage");
                scenes.add("disgust");
                scenes.add("watermark");
                scenes.add("quality");
                //scenes.add("accurate");
                scenes.add("accuratebasic");

            }

            input.put("scenes", scenes);
            if(sceneConf!=null){
                input.put("sceneConf", sceneConf);
            }
            String params = JSON.toJSONString(input);
            String contentType="application/json;charset=utf-8";
            String result = HttpUtil.post(URL_img_censor, getAccessToken(), params,contentType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        String path="C:\\Users\\Administrator\\Desktop\\sex.jpg";
        String res=  img_censor(path, IMG_TYPE.BASE64, null,null);
        System.out.println(res);
        //path="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528948993854&di=1a3900171fc9d1684364785e2539f436&imgtype=0&src=http%3A%2F%2Fp18.qhimg.com%2Fbdr%2F__%2Fd%2F_open360%2Ffresh0218%2F21.jpg";
        //  userDefined(path,null);
        //path="C:\\Users\\Administrator\\Desktop\\h3.jpg";
        //faceAudit(path,null);
    }
}
