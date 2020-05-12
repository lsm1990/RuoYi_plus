package com.ruoyi.third.baidu.bean.imgClassify;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 *  Logo结果识别
 * @author wujiyue
 */
public class LogoResult  extends BaseResult {
    //{"log_id": 3510281299570976969, "result_num": 1, "result": [{"type": 0, "name": "香奈儿", "probability": 1, "location": {"width": 288, "top": 35, "left": 164, "height": 224}}]}
    //{"log_id": 6564867818478827737, "result_num": 2, "result": [{"type": 0, "name": "泰格豪雅", "probability": 1, "location": {"width": 120, "top": 160, "left": 3, "height": 126}}, {"type": 0, "name": "泰格豪雅", "probability": 0.99916702508926, "location": {"width": 462, "top": 139, "left": 143, "height": 102}}]}
    //{"log_id": 2325742685649815616, "result_num": 0, "result": []}
    private Integer result_num;
    /**
     * 是否菜品
     */
    private boolean isLogo;
    /**
     * 认定为是哪个菜品的阈值概率
     */
    private Double logoProbability=0.7;
    /**
     * 根据阈值概率最总认定为是那个菜品名称
     */
    private String result_name;
    /**
     *定为是那个菜品的概率
     */
    private Double result_probability;

    private Integer result_type;
    private Map result_location;
    /**
     * 如 返回概率大于0.1的结果   Map 中   key:type、name、location、probability
     */
    private List<Map> result;

    public LogoResult(String json){
        this.setJson(json);
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            this.setLog_id(jsonObject.getString("log_id"));
            String e_code=String.valueOf(jsonObject.get("error_code"));
            if(notNull(e_code)){
                this.setError_code(e_code);
                String e_msg=String.valueOf(jsonObject.get("error_msg"));
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else {
                this.setRequestOk(true);
                this.result_num=jsonObject.getInteger("result_num");
                if(result_num==0){
                    isLogo=false;
                    this.result_name="非Logo";
                    return;
                }
                List<Map> list = JSONArray.parseArray(jsonObject.get("result").toString(), Map.class);
                if(list!=null&&list.size()>0){
                    for(Map t:list){
                        String name=String.valueOf(t.get("name"));
                        Double pro=Double.valueOf(String.valueOf(t.get("probability")));
                        Integer type=Integer.valueOf(String.valueOf(t.get("type")));
                        //Map loMap=(Map) t.get("location")
                        Map loMap= JSONObject.parseObject(String.valueOf(t.get("location")),Map.class);
                        if(pro>logoProbability){
                            isLogo=true;
                            this.result_name=name;
                            this.result_probability=pro;
                            this.result_type=type;
                            this.result_location=loMap;
                        }

                    }
                    this.result=list;
                }


            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static LogoResult create(String jsonStr) {
        return new LogoResult(jsonStr);
    }

    @Override
    public String toString() {
        if(isRequestOk()){
            return "LogoResult{" +
                    "result_num=" + result_num +
                    ", isLogo=" + isLogo +
                    ", result_name='" + result_name + '\'' +
                    ", result_probability=" + result_probability +
                    ", result_type=" + result_type +
                    ", result_location=" + result_location +
                    ", result=" + result +
                    '}';
        }else{
            return getError_msg()+";错误代码["+getError_code()+"]"+";log_id["+getLog_id()+"]";
        }

    }

    public Integer getResult_num() {
        return result_num;
    }

    public void setResult_num(Integer result_num) {
        this.result_num = result_num;
    }

    public boolean isLogo() {
        return isLogo;
    }

    public void setLogo(boolean isLogo) {
        this.isLogo = isLogo;
    }

    public Double getLogoProbability() {
        return logoProbability;
    }

    public void setLogoProbability(Double logoProbability) {
        this.logoProbability = logoProbability;
    }

    public String getResult_name() {
        return result_name;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    public Double getResult_probability() {
        return result_probability;
    }

    public void setResult_probability(Double result_probability) {
        this.result_probability = result_probability;
    }

    public Integer getResult_type() {
        return result_type;
    }

    public void setResult_type(Integer result_type) {
        this.result_type = result_type;
    }

    public Map getResult_location() {
        return result_location;
    }

    public void setResult_location(Map result_location) {
        this.result_location = result_location;
    }

    public List<Map> getResult() {
        return result;
    }

    public void setResult(List<Map> result) {
        this.result = result;
    }
}
