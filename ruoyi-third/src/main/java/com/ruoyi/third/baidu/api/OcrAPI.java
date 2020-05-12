package com.ruoyi.third.baidu.api;



import com.ruoyi.third.baidu.util.Base64Util;
import com.ruoyi.third.baidu.util.FileUtil;
import com.ruoyi.third.baidu.util.HttpUtil;

import java.net.URLEncoder;

/**
 * 文字识别
 * @author wujiyue
 */
public class OcrAPI extends BaiduAi{

    private static final String URL_general_basic="https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";//通用文字识别
    private static final String URL_idcard="https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";//身份证字体识别
    private static final String URL_bankcard="https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
    private static final String URL_driving_license="https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license";
    private static final String URL_license_plate="https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate";//车牌号识别
    private static final String URL_number="https://aip.baidubce.com/rest/2.0/ocr/v1/numbers";
    private static final String URL_qrcode="https://aip.baidubce.com/rest/2.0/ocr/v1/qrcode";
    private static final String URL_excel_request="https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";
    private static final String URL_excel_get_request_result="https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/get_request_result";

    /**
     * 通用文字识别
     * @param path
     * @return
     */
    public static String  general_basic(String path){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_general_basic, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //身份证字体识别
    public static String   idcardOcr(String path,Boolean isBackGround){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "";
            if(isBackGround){
                params += "id_card_side=back&" ;
            }else{
                params += "id_card_side=front&" ;
            }
            params += URLEncoder.encode("image", "UTF-8") + "="+ URLEncoder.encode(imgStr, "UTF-8");

            String result = HttpUtil.post(URL_idcard, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }
    //银行卡识别
    public static String bankCardOcr(String path){

        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_bankcard, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String drivingLicenseOcr(String path){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_driving_license, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //车牌号识别
    public static String license_plateOcr(String path){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_license_plate, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //数字识别(额外申请权限)
    protected static String numberOcr(String path){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_number, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //二维码识别(额外申请权限)
    protected static String qrcode(String path){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_qrcode, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //表格文字识别(异步接口)
    //1.请求接口
    protected static String excel_request(String path){
        try {
            byte[] imgData = FileUtil.readFileByBytes(path);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(URL_excel_request, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 2.获取请求结果
     * @param request_id
     * @param resultType  excel：返回生成的excel的网络地址  json:返回json数据
     * @return
     */
    protected static String excel_get_request_result(String request_id,String resultType){
        try {
            String params = "request_id=" + request_id+"&result_type="+resultType;//result_type默认为excel返回excel生成地址
            String result = HttpUtil.post(URL_excel_get_request_result, getAccessToken(), params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        /*String path="C:\\Users\\Administrator\\Desktop\\id.jpg";
        String s=idcardOcr(path,false);
        System.out.println(s);*/
        /*String path="C:\\Users\\Administrator\\Desktop\\bank2.jpg";
        String s=bankCardOcr(path);
        System.out.println(s);*/

        /*String path="C:\\Users\\Administrator\\Desktop\\drive.jpeg";
        String s=drivingLicenseOcr(path);
        System.out.println(s);*/
        /*String path="C:\\Users\\Administrator\\Desktop\\plate.png";
        String s=license_plateOcr(path);
        System.out.println(s);*/
       /* String path="C:\\Users\\Administrator\\Desktop\\num.jpg";
        String s=numberOcr(path);
        System.out.println(s);*/

       /* String path="C:\\Users\\Administrator\\Desktop\\sex.jpg";
        String s=qrcode(path);
        System.out.println(s);*/

        //   String path="C:\\Users\\Administrator\\Desktop\\excel.jpg";
        //    String request_id=excel_request(path);
        //    System.out.println(request_id);
        //   String s=excel_get_request_result("11394947_336225","excel");
        //    System.out.println(s);
        String path="C:\\Users\\Administrator\\Desktop\\2.jpg";
        String res=general_basic(path);
        System.out.println(res);
    }
}
