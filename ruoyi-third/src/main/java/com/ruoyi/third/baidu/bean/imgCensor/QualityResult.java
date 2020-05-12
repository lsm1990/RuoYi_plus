package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;


/**
 *  图片质量返回结果
 * @author wujiyue
 */
public class QualityResult extends BaseResult {

    private Double aesthetic;//美观度
    private Double clarity;//清晰度

    public static QualityResult create(String jsonStr) {
        return new QualityResult(jsonStr);
    }

    public QualityResult(String json){
        this.setJson(json);
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            String e_code=String.valueOf(jsonObject.get("error_code"));
            if(notNull(e_code)){
                this.setError_code(e_code);
                String e_msg=String.valueOf(jsonObject.get("error_msg"));
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else{
                this.setRequestOk(true);
                this.setLog_id(jsonObject.getString("log_id"));
                JSONObject quality_json= JSONObject.parseObject(json);
                JSONObject res_json= quality_json.getJSONObject("result");
                aesthetic=res_json.getDouble("aesthetic");
                clarity=res_json.getDouble("clarity");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }

    @Override
    public String toString() {
        return "QualityResult{" +
                super.toString()+
                "aesthetic=" + aesthetic +
                ", clarity=" + clarity +
                '}';
    }

    public Double getAesthetic() {
        return aesthetic;
    }

    public void setAesthetic(Double aesthetic) {
        this.aesthetic = aesthetic;
    }

    public Double getClarity() {
        return clarity;
    }

    public void setClarity(Double clarity) {
        this.clarity = clarity;
    }
}
