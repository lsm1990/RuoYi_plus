package com.ruoyi.third.baidu.api;

/**
 * Created by Administrator on 2018-06-13.
 */
public class BaiduAi extends AuthService{




    public enum AiType{
        faceDetect, //人脸检测
        plant,//植物识别
        bankCard,//银行卡识别
        idCard,//身份证识别
        plate,//车牌号识别
        driver,//驾驶证识别
        animal,//动物识别
        car,//车型识别
        dish,//菜品识别
        general_basic,//通用文字识别
    }
    public enum IMG_CENSOR_SCENES{
        ocr, //通用文字识别
        politician,//政治敏感识别
        antiporn,//色情识别
        terror,//暴恐识别
        webimage,//网络图片文字识别
        disgust,//恶心图像识别
        watermark,//广告检测
        quality,//图像质量检测
        accurate,//通用文字识别（高精度含位置版）
        accuratebasic  //通用文字识别（高精度版）
    }
    public enum IMAGE_VERIFY_TYPE{
        SE_QING("色情", 1), XING_GAN("性感", 2), BAO_KONG("暴恐", 3), E_XIN("恶心", 4),SHUI_YIN("水印",5),ER_WEI_MA("二维码",6),TIAO_XING_MA("条形码",7),ZHENG_ZHI_REN_WU("政治人物",8)
        ,MIN_GAN_CI("敏感词",9),ZI_DING_YI("自定义敏感词",10),GONG_ZHONG_REN_WU("存在公众人物",11);
        // 构造方法
        private IMAGE_VERIFY_TYPE(String name, int index) {
            this.name = name;
            this.index = index;
        }
        // 成员变量
        private String name;
        private int index;
        // 普通方法
        public static String getName(int index) {
            for (IMAGE_VERIFY_TYPE c : IMAGE_VERIFY_TYPE.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }
        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }

    }
    /**
     * 	图片类型
     * BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；
     * URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
     * FACE_TOKEN: 人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
     */
    public enum IMG_TYPE{
        BASE64,
        URL,
        FACE_TOKEN
    }

    /**
     * 人脸的类型
     * LIVE：表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等，
     * IDCARD：表示身份证芯片照：二代身份证内置芯片中的人像照片，
     * WATERMARK：表示带水印证件照：一般为带水印的小图，如公安网小图
     * CERT：表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
     */
    public enum FACE_TYPE{
        LIVE,
        IDCARD,
        WATERMARK,
        CERT
    }

    /**
     * 图片质量控制
     * NONE: 不进行控制
     * LOW:较低的质量要求
     * NORMAL: 一般的质量要求
     * HIGH: 较高的质量要求
     默认 NONE
     */
    public enum QUALITY_CONTROL{
        NONE,
        LOW,
        NORMAL,
        HIGH
    }

    /**
     * 活体检测控制
     * NONE: 不进行控制
     * LOW:较低的活体要求(高通过率 低攻击拒绝率)
     * NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
     * HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
     * 默认 NONE
     */
    public enum LIVENESS_CONTROL{
        NONE,
        LOW,
        NORMAL,
        HIGH
    }
}
