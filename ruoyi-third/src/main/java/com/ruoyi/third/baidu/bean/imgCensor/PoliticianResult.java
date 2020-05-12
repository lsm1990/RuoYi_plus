package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

/**
 * 政治敏感结果
 * @author wujiyue
 */
public class PoliticianResult extends BaseResult {


    private Integer result_num;

    private boolean notPolitician;//非政治敏感标志 true 非政治敏感   false:政治敏感
    public static PoliticianResult create(String jsonStr) {
        return new PoliticianResult(jsonStr);
    }

    public PoliticianResult(String json){
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
                this.result_num=jsonObject.getInteger("result_num");
                this.setLog_id(jsonObject.getString("log_id"));
                if("否".equals(jsonObject.getString("include_politician"))){
                    this.notPolitician=true;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }

    public Integer getResult_num() {
        return result_num;
    }

    public void setResult_num(Integer result_num) {
        this.result_num = result_num;
    }

    public boolean isNotPolitician() {
        return notPolitician;
    }

    public void setNotPolitician(boolean notPolitician) {
        this.notPolitician = notPolitician;
    }

    @Override
    public String toString() {
        return "PoliticianResult{" +
                "result_num=" + result_num +
                ", notPolitician=" + notPolitician +
                '}';
    }
}
