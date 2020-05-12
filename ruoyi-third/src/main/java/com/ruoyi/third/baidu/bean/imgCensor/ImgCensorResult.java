package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.api.BaiduAi;
import com.ruoyi.third.baidu.bean.BaseResult;


/**
 * 图片识别综合返回结果
 *  图像审核   组合服务接口 返回结果实体类
 * @author wujiyue
 */
public class ImgCensorResult extends BaseResult {
    /**
     * 反色情检测
     */
    private boolean AntipornFlag;
    /**
     *网络图识别
     */
    private boolean WebimageFlag;
    /**
     *图片质量检测
     */
    private boolean QualityFlag;
    /**
     *通用文字识别
     */
    private boolean OcrFlag;
    /**
     *文字精确识别
     */
    private boolean AccuratebasicFlag;
    /**
     *恶心检查
     */
    private boolean DisgustFlag;
    /**
     *水印、广告
     */
    private boolean WatermarkFlag;
    /**
     *政治敏感
     */
    private boolean PoliticianFlag;


    private AntipornResult antipornResult;
    private WebimageResult webimageResult;
    private QualityResult qualityResult;
    private OcrResult ocrResult;
    private AccuratebasicResult accuratebasicResult;
    private DisgustResult disgustResult;
    private WatermarkResult watermarkResult;
    private PoliticianResult politicianResult;
    public ImgCensorResult(String jsonStr){
        this.setJson(jsonStr);
        try {
            JSONObject jsonObject= JSONObject.parseObject(jsonStr);
            String t_error_code=String.valueOf(jsonObject.get("error_code"));

            if(notNull(t_error_code)) {
                this.setRequestOk(false);
                this.setError_code(t_error_code);
                String t_error_msg=String.valueOf(jsonObject.get("error_msg"));
                this.setError_msg(t_error_msg);
            }else{
                this.setRequestOk(true);
                this.setLog_id(jsonObject.getString("log_id"));
                JSONObject result_json=jsonObject.getJSONObject("result");
                for (BaiduAi.IMG_CENSOR_SCENES e : BaiduAi.IMG_CENSOR_SCENES.values()) {
                    JSONObject o=result_json.getJSONObject(e.name());
                    if(o!=null){
                        if(e== BaiduAi.IMG_CENSOR_SCENES.antiporn){//色情识别
                            AntipornFlag=true;
                            this.antipornResult=AntipornResult.create(o.toJSONString());
                        }else if(e== BaiduAi.IMG_CENSOR_SCENES.webimage){//网络图片文字识别
                            WebimageFlag=true;
                            this.webimageResult=WebimageResult.create(o.toJSONString());
                        }else if(e==BaiduAi.IMG_CENSOR_SCENES.quality){//图片质量检测
                            QualityFlag=true;
                            qualityResult=QualityResult.create(o.toJSONString());
                        }else if(e==BaiduAi.IMG_CENSOR_SCENES.ocr){//通用文字识别
                            OcrFlag=true;
                            ocrResult=OcrResult.create(o.toJSONString());
                        }else if(e==BaiduAi.IMG_CENSOR_SCENES.accuratebasic){//精确的文字识别
                            AccuratebasicFlag=true;
                            accuratebasicResult=AccuratebasicResult.create(o.toJSONString());
                        }else if(e==BaiduAi.IMG_CENSOR_SCENES.disgust){//恶心
                            DisgustFlag=true;
                            disgustResult=DisgustResult.create(o.toJSONString());
                        }else if(e==BaiduAi.IMG_CENSOR_SCENES.watermark){//水印、广告
                            WatermarkFlag=true;
                            watermarkResult=WatermarkResult.create(o.toJSONString());
                        }else if(e==BaiduAi.IMG_CENSOR_SCENES.politician){//政治敏感
                            PoliticianFlag=true;
                            politicianResult=PoliticianResult.create(o.toJSONString());
                        }
                    }

                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static ImgCensorResult create(String jsonStr) {
        return new ImgCensorResult(jsonStr);
    }

    @Override
    public String toString() {
        return "ImgCensorResult{" +
                "AntipornFlag=" + AntipornFlag +
                ", WebimageFlag=" + WebimageFlag +
                ", QualityFlag=" + QualityFlag +
                ", OcrFlag=" + OcrFlag +
                ", AccuratebasicFlag=" + AccuratebasicFlag +
                ", DisgustFlag=" + DisgustFlag +
                ", WatermarkFlag=" + WatermarkFlag +
                ", PoliticianFlag=" + PoliticianFlag +
                ", antipornResult=" + antipornResult +
                ", webimageResult=" + webimageResult +
                ", qualityResult=" + qualityResult +
                ", ocrResult=" + ocrResult +
                ", accuratebasicResult=" + accuratebasicResult +
                ", disgustResult=" + disgustResult +
                ", watermarkResult=" + watermarkResult +
                ", politicianResult=" + politicianResult +
                '}';
    }
}
