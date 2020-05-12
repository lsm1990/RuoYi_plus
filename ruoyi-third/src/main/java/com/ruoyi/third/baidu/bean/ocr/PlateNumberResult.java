package com.ruoyi.third.baidu.bean.ocr;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;


/**
 * 车牌号
 * @author wujiyue
 */
public class PlateNumberResult extends BaseResult {

    private String color;
    private String plateNumber;

    public PlateNumberResult(String json){
        this.setJson(json);
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            String e_code=jsonObject.getString("error_code");
            if(notNull(e_code)){
                this.setError_code(e_code);
                String e_msg=jsonObject.getString("error_msg");
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else {
                this.setRequestOk(true);
                this.setLog_id(jsonObject.getString("log_id"));
                JSONObject words_result_json=jsonObject.getJSONObject("words_result");
                this.color=words_result_json.getString("color");
                this.plateNumber=words_result_json.getString("number");
         }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static PlateNumberResult create(String jsonStr) {
        return new PlateNumberResult(jsonStr);
    }

    @Override
    public String toString() {
        return "PlateNumberResult{" +
                "color='" + color + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
