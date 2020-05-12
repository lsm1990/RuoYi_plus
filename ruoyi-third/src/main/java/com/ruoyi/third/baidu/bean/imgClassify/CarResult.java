package com.ruoyi.third.baidu.bean.imgClassify;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 *  车型识别返回结果
 * @author wujiyue
 */
public class CarResult extends BaseResult {

    //{"log_id": 6322863901722339584, "location_result": {"width": 729, "top": 193, "height": 302, "left": 132}, "result": [{"score": 0.99944090843201, "name": "宝骏560", "year": "2015-2017"}, {"score": 0.00022018732852302, "name": "宝骏730", "year": "2016-2017"}, {"score": 0.00013495099847205, "name": "东风风行风行SX6", "year": "2016"}, {"score": 1.4159925740387e-05, "name": "西雅特Ateca", "year": "无年份信息"}, {"score": 1.1880139027198e-05, "name": "东风风度MX5", "year": "2016-2017"}, {"score": 1.1864442967635e-05, "name": "大众途昂", "year": "2017"}, {"score": 9.8557611636352e-06, "name": "本田Pilot", "year": "无年份信息"}, {"score": 9.3120852397988e-06, "name": "开瑞K60", "year": "2017"}, {"score": 9.2856907940586e-06, "name": "标致3008", "year": "2013-2016"}, {"score": 8.5839219536865e-06, "name": "双龙途凌", "year": "2016-2017"}, {"score": 6.7547152866609e-06, "name": "江淮瑞风S系列", "year": "2017"}, {"score": 6.1060736697982e-06, "name": "北汽幻速幻速S6", "year": "2016-2017"}], "color_result": "白色"}
    //{"log_id": 4560085690705486552, "location_result": {"width": 0, "top": 0, "height": 0, "left": 0}, "result": [{"score": 0.5, "name": "非车类", "year": "无年份信息"}, {"score": 0.050989761948586, "name": "福特福睿斯", "year": "2015-2017"}], "color_result": null}
    private Map location_result;
    private List<Map> result;
    /**
     * 是否车辆
     */
    private boolean isCar;
    /**
     * 认定为是哪个车型的阈值概率
     */
    private Double carProbability=0.7;
    /**
     *根据阈值概率最总认定为是那个菜品名称
     */
    private String result_name;
    /**
     *认定为是这个车型的概率
     */
    private Double result_probability;
    /**
     *年份
     */
    private String result_year;

    public CarResult(String json){
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

                this.location_result=jsonObject.getObject("location_result",Map.class);
                if(this.location_result!=null){
                    Integer width=Integer.valueOf(String.valueOf(location_result.get("width")));
                    Integer height=Integer.valueOf(String.valueOf(location_result.get("height")));
                    Integer top=Integer.valueOf(String.valueOf(location_result.get("top")));
                    Integer left=Integer.valueOf(String.valueOf(location_result.get("left")));
                    if(width==0&&height==0&&top==0&&left==0){
                        isCar=false;
                    }
                }
                List<Map> list = JSONArray.parseArray(jsonObject.get("result").toString(), Map.class);
                if(list!=null && list.size()>0){
                    for(Map t:list){
                        String name=String.valueOf(t.get("name"));
                        Double pro=Double.valueOf(String.valueOf(t.get("score")));
                        String year=String.valueOf(t.get("year"));
                        if(pro >= 0.45 && "非车类".equals(name)){
                            isCar=false;
                            this.result_name=name;
                            this.result_probability=pro;
                            break;
                        }
                        if(pro>carProbability){
                            isCar=true;
                            this.result_name=name;
                            this.result_probability=pro;
                            this.result_year=year;
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
    public static CarResult create(String jsonStr) {
        return new CarResult(jsonStr);
    }

    public Map getLocation_result() {
        return location_result;
    }

    public void setLocation_result(Map location_result) {
        this.location_result = location_result;
    }

    public List<Map> getResult() {
        return result;
    }

    public void setResult(List<Map> result) {
        this.result = result;
    }

    public boolean isCar() {
        return isCar;
    }

    public void setCar(boolean isCar) {
        this.isCar = isCar;
    }

    public Double getCarProbability() {
        return carProbability;
    }

    public void setCarProbability(Double carProbability) {
        this.carProbability = carProbability;
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

    public String getResult_year() {
        return result_year;
    }

    public void setResult_year(String result_year) {
        this.result_year = result_year;
    }

    @Override
    public String toString() {
        if(isRequestOk()){
            return "CarResult{" +
                    "location_result=" + location_result +
                    ", result=" + result +
                    ", isCar=" + isCar +
                    ", result_name='" + result_name + '\'' +
                    ", result_probability=" + result_probability +
                    ", result_year='" + result_year + '\'' +
                    '}';
        }else{
            return getError_msg()+";错误代码["+getError_code()+"]"+";log_id["+getLog_id()+"]";
        }

    }
}
