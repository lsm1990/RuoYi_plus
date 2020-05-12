package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * @author wujiyue
 */
public class AntipornResult extends BaseResult {

    /**
     * true 合规  false 不合规
     */
    private boolean result;
    private String conclusion;
    /**
     * 色情的
     */
    private Double Conclusion_probability_pornographic;
    /**
     * 性感的
     */
    private Double Conclusion_probability_sexy;
    /**
     *正常的
     */
    private Double Conclusion_probability_normal;

    @Override
    public String toString() {
        return "AntipornResult{" +
                super.toString()+
                "result=" + result +
                ", conclusion='" + conclusion + '\'' +
                ", Conclusion_probability_pornographic=" + Conclusion_probability_pornographic +
                ", Conclusion_probability_sexy=" + Conclusion_probability_sexy +
                ", Conclusion_probability_normal=" + Conclusion_probability_normal +
                '}';
    }

    public AntipornResult(String json){
        this.setJson(json);
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            String t_error_code=String.valueOf(jsonObject.get("error_code"));
            this.setLog_id( String.valueOf(jsonObject.get("log_id")));
            if(!notNull(t_error_code)){
                this.setRequestOk(true);
                result=true;
                this.setError_code(null);
                this.setError_msg(null);
                JSONObject antiporn_json= JSONObject.parseObject(json);
                List<Map> resList= JSONArray.parseArray( antiporn_json.get("result").toString(),Map.class);//正常\色情\性感三个维度的概率
                if(resList!=null&&resList.size()>0){
                    for(Map tm:resList){
                        if("色情".equals(tm.get("class_name"))){
                            this.Conclusion_probability_pornographic = Double.valueOf(String.valueOf(tm.get("probability")));
                        }else if("正常".equals(tm.get("class_name"))){
                            this.Conclusion_probability_normal = Double.valueOf(String.valueOf(tm.get("probability")));
                        }else if("性感".equals(tm.get("class_name"))){
                            this.Conclusion_probability_sexy = Double.valueOf(String.valueOf(tm.get("probability")));
                        }
                    }
                }
                String temp = String.valueOf(antiporn_json.get("conclusion"));//审核最终结果：正常\色情\性感
                this.conclusion=temp;
               /* this.description=temp;
                if("正常".equals(temp)){
                    this.conclusion=this.Conclusion_normal;
                }else if("色情".equals(temp)){
                    this.conclusion=this.Conclusion_pornographic;
                }else if("性感".equals(temp)){
                    this.conclusion=this.Conclusion_sexy;
                }*/
            }else{
                this.setRequestOk(false);
                this.setError_code(t_error_code);
                String t_error_msg=String.valueOf(jsonObject.get("error_msg"));
                this.setError_msg(t_error_msg);
                result=false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }

    }
    public static AntipornResult create(String jsonStr) {
        return new AntipornResult(jsonStr);
    }

}
