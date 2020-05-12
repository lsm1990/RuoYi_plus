package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * @author wujiyue
 */
public class WatermarkResult extends BaseResult {



    private Integer result_num;
    private boolean hasWaterMark;
    private boolean hasQrcode;

    public WatermarkResult(String json){
        this.setJson(json);
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            String e_code=String.valueOf(jsonObject.get("error_code"));
            if(notNull(e_code)){
                this.setError_code(e_code);
                String e_msg=String.valueOf(jsonObject.get("error_msg"));
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else {
                this.setRequestOk(true);
                this.setLog_id(jsonObject.getString("log_id"));
                this.result_num=jsonObject.getInteger("result_num");
                List<Map> list = JSONArray.parseArray(jsonObject.get("result").toString(), Map.class);
                if(list!=null&&list.size()>0){
                    for(Map t:list){
                        if("QR code".equals(t.get("type"))){
                            this.hasQrcode=true;
                            break;
                        }
                        if("watermark".equals(t.get("type"))){
                            this.hasWaterMark=true;
                            break;
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
    public static WatermarkResult create(String jsonStr) {
        return new WatermarkResult(jsonStr);
    }
    @Override
    public String toString() {
        return "WatermarkResult{" +
                "result_num=" + result_num +
                ", hasWaterMark=" + hasWaterMark +
                ", hasQrcode=" + hasQrcode +
                '}';
    }


}
