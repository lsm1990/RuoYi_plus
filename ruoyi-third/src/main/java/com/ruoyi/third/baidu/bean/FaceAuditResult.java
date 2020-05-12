package com.ruoyi.third.baidu.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 头像审核结果实体类
 * @author wujiyue
 */
public class FaceAuditResult {


    public static FaceAuditResult create(String jsonStr) {
        return new FaceAuditResult(jsonStr);
    }

    private String json;//json字符串
    private String log_id;//
    private String error_code;//错误码，错误才返回
    private String error_msg;//错误提示，错误才返回


   private  static Map<Integer,String>  res_msgMap = new HashMap<Integer, String>();
    private Integer[] res_msg;//
    private String description="";//对 res_msg 的描述

    private boolean result;//true 合规  false 不合规
    private String conclusion;
    private Double Conclusion_probability_pornographic;//色情的
    private Double Conclusion_probability_sexy;//性感的
    private Double Conclusion_probability_normal;//正常的


    public FaceAuditResult(String json){
        res_msgMap.put(101 , "文字中包含手机号码");
        res_msgMap.put(102 , "文字中包含“QQ”号字样，或包含超过9位的连续数字");
        res_msgMap.put(103 , "文字中包含竞品名称");
        res_msgMap.put(104 , "文字中包含疑似手机号/QQ的连续数字");
        res_msgMap.put(201 , "图片中不包含人脸");
        res_msgMap.put(202 , "图片中包含人脸");
        res_msgMap.put(203 , "图片中包含多个人脸（默认为>=2）");
        res_msgMap.put(204 , "图片中包含明星脸");
        res_msgMap.put(205 , "图片中包含政治人物人脸");
        res_msgMap.put(206 , "图片中包含公众人物人脸");
        res_msgMap.put(207 , "自定义人脸库识别未通过");

        res_msgMap.put(301 , "图片中包含色情内容");
        res_msgMap.put(302 , "图片中包含性感内容，如穿着比较暴露");
        res_msgMap.put(401 , "图片中包含血腥暴力场景内容");
        res_msgMap.put(501 , "图像美观度低于阀值");
        res_msgMap.put(502 , "图像美观度高于阀值");
        res_msgMap.put(503 , "图像美观度不等于阀值");

        this.json=json;
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            String t_error_code=String.valueOf(jsonObject.get("error_code"));

            if(t_error_code==null||"".equals(t_error_code)||"null".equals(t_error_code)){
                result=true;
                this.error_code=null;
                this.error_msg=null;
                this.log_id = String.valueOf(jsonObject.get("log_id"));
                JSONObject result0=jsonObject.getJSONArray("result").getJSONObject(0);
                List<Integer> res_msg_List= JSONArray.parseArray( result0.get("res_msg").toString(),Integer.class);
                if(res_msg_List!=null&&res_msg_List.size()>0){
                    res_msg = res_msg_List.toArray(new Integer[res_msg_List.size()]);//能正确运行
                    for(Integer a:res_msg_List){
                        this.description += res_msgMap.get(a)+";";
                    }
                }
                JSONObject antiporn_json=result0.getJSONObject("data").getJSONObject("antiporn");
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
                this.error_code=t_error_code;
                result=false;
                String t_error_msg=String.valueOf(jsonObject.get("error_msg"));
                this.error_msg=t_error_msg;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            result=false;
            description="json字符串解析异常!";
        }

    }

    @Override
    public String toString() {
        return "FaceAuditResult{" +
                "log_id='" + log_id + '\'' +
                ", error_code='" + error_code + '\'' +
                ", error_msg='" + error_msg + '\'' +
                ", res_msg=" + Arrays.toString(res_msg) +
                ", description='" + description + '\'' +
                ", result=" + result +
                ", conclusion='" + conclusion + '\'' +
                ", Conclusion_probability_pornographic=" + Conclusion_probability_pornographic +
                ", Conclusion_probability_sexy=" + Conclusion_probability_sexy +
                ", Conclusion_probability_normal=" + Conclusion_probability_normal +
                '}';
    }
}
