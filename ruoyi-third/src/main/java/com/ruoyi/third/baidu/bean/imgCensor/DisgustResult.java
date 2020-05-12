package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

/**
 * 恶心判定结果
 * @author wujiyue
 */
public class DisgustResult extends BaseResult {

    private Double result;
    private boolean isDisgust;
    public DisgustResult(String json){
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
                this.result=jsonObject.getDouble("result");
                if(this.result>0.5f){
                    this.isDisgust=true;
                }else{
                    this.isDisgust=false;
                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }

    @Override
    public String toString() {
        return "DisgustResult{" +
                "result=" + result +
                ", isDisgust=" + isDisgust +
                '}';
    }

    public static DisgustResult create(String jsonStr) {
        return new DisgustResult(jsonStr);
    }
}
