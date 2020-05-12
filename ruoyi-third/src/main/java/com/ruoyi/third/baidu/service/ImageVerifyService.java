package com.ruoyi.third.baidu.service;


import com.ruoyi.third.baidu.api.BaiduAi;
import com.ruoyi.third.baidu.api.ImageVerifyAPI;
import com.ruoyi.third.baidu.bean.FaceAuditResult;
import com.ruoyi.third.baidu.bean.ImageVerifyResult;
import com.ruoyi.third.baidu.bean.imgCensor.ImgCensorResult;

/**
 * @author wujiyue
 */
public class ImageVerifyService {

    public static void main(String[] args){
        //String json="{\"conclusion\":\"不合规\",\"log_id\":152894199165352,\"data\":[{\"msg\":\"存在水印码内容\",\"probability\":0.9978612,\"type\":5},{\"msg\":\"存在公众人物\",\"stars\":[{\"probability\":0.6516758,\"name\":\"血纯茗雅\"}],\"type\":11}],\"conclusionType\":2}\n";
        //ImageVerifyResult result= ImageVerifyResult.create(json);

         String path="C:\\Users\\wjy\\Desktop\\plant.jpg";
        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528955462762&di=7f268358cd9b9eb5da6725afd52cb639&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Feaf81a4c510fd9f900630df72f2dd42a2834a43c.jpg";
        url="http://mmbiz.qpic.cn/mmbiz_jpg/cZsgWN5KwlADM4BX5OJxnr3tJGkpTyzfNCQXyhvNDz6HqN6ds1qE4ZiczHGicYDZeLS39LIHNiaS0ElQohDnJVpPw/0";
         ImageVerifyResult result=imageVerify_localPath(path);
        // ImageVerifyResult result=imageVerify_netPath(url);
        System.out.println(result);

        /*String path="C:\\Users\\Administrator\\Desktop\\h7.jpg";
        FaceAuditResult result=faceAudit_localPath(path);
        System.out.println(result);*/
        /*String path="C:\\Users\\Administrator\\Desktop\\yzm.png";
        ImgCensorResult result =  img_censor_localPath(path);
        System.out.println(result);*/
    }

    /**
     * 检查本地图片是否合规
     * @param path
     * @return
     */
    public static ImageVerifyResult imageVerify_localPath(String path){
      String json = ImageVerifyAPI.userDefined(path, BaiduAi.IMG_TYPE.BASE64);
      return ImageVerifyResult.create(json);
    }

    /**
     * 检查一个网络路径文件是否合规
     * @param url
     * @return
     */
    public static ImageVerifyResult imageVerify_netPath(String url){
        String json = ImageVerifyAPI.userDefined(url, BaiduAi.IMG_TYPE.URL);
        return ImageVerifyResult.create(json);
    }

    /**
     * 头像审核（本地图片）
     * @param path
     * @return
     */
    public static FaceAuditResult faceAudit_localPath(String path){
        String json = ImageVerifyAPI.faceAudit(path, BaiduAi.IMG_TYPE.BASE64);
        FaceAuditResult result = FaceAuditResult.create(json);
        return result;
    }
    /**
     * 头像审核（网络图片）
     * @param path
     * @return
     */
    public static FaceAuditResult faceAudit_netPath(String path){
        String json = ImageVerifyAPI.faceAudit(path, BaiduAi.IMG_TYPE.URL);
        return FaceAuditResult.create(json);
    }

    public static ImgCensorResult img_censor_localPath(String path){
        String json = ImageVerifyAPI.img_censor(path, BaiduAi.IMG_TYPE.BASE64,null,null);
        return ImgCensorResult.create(json);
    }
}
