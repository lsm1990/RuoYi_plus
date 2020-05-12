package com.ruoyi.third.baidu.bean.imgClassify;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * 菜品识别返回结果
 * @author wujiyue
 */
public class DishResult extends BaseResult {

    /*{
        "log_id": 2471272194,
            "result_num": 5,
            "result": [
        {
            "name": "烧烤（串类）",
                "calorie": 333.33,
                "probability": 0.35874313116074
        },
        {
            "name": "鱿鱼",
                "calorie": 333.33,
                "probability": 0.20610593259335
        },
        {
            "name": "板筋",
                "calorie": 333.33,
                "probability": 0.15860831737518
        },
        {
            "name": "鸡脆骨",
                "calorie": 333.33,
                "probability": 0.077698558568954
        },
        {
            "name": "麻辣烫",
                "calorie": 333.33,
                "probability": 0.041968926787376
        }
        ]

    }
   {"log_id": 2677811678621716503, "result_num": 1, "result": [{"calorie": "-1", "has_calorie": true, "name": "非菜", "probability": "0.202248"}]}
    */
    private Integer result_num;
    /**
     * 是否菜品
     */
    private boolean isDish;
    /**
     * 认定为是哪个菜品的阈值概率
     */
    private Double dishProbability=0.7;
    /**
     *根据阈值概率最总认定为是那个菜品名称
     */
    private String result_name;
    /**
     *定为是那个菜品的概率
     */
    private Double result_probability;
    /**
     *
     */
    private Double result_calorie;
    /**
     *如 返回概率大于0.1的结果   Map 中   key:name、calorie、probability
     */
    private List<Map> result;

    public DishResult(String json){
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
                List<Map> list = JSONArray.parseArray(jsonObject.get("result").toString(), Map.class);
                if(list!=null&&list.size()>0){
                    for(Map t:list){
                        String name=String.valueOf(t.get("name"));
                        Double pro=Double.valueOf(String.valueOf(t.get("probability")));
                        Double cal=Double.valueOf(String.valueOf(t.get("calorie")));
                        if((result_num==1&&"非菜".equals(name))||(pro>0.5&&"非菜".equals(name))){
                            isDish=false;
                            this.result_name=name;
                            this.result_probability=pro;
                            break;
                        }
                        if(pro>dishProbability){
                            isDish=true;
                            this.result_name=name;
                            this.result_probability=pro;
                            this.result_calorie=cal;
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
    public static DishResult create(String jsonStr) {
        return new DishResult(jsonStr);
    }

    public List<Map> getResult() {
        return result;
    }

    public void setResult(List<Map> result) {
        this.result = result;
    }

    public Integer getResult_num() {
        return result_num;
    }

    public void setResult_num(Integer result_num) {
        this.result_num = result_num;
    }

    public boolean isDish() {
        return isDish;
    }

    public void setDish(boolean isDish) {
        this.isDish = isDish;
    }

    public Double getDishProbability() {
        return dishProbability;
    }

    public void setDishProbability(Double dishProbability) {
        this.dishProbability = dishProbability;
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

    public Double getResult_calorie() {
        return result_calorie;
    }

    public void setResult_calorie(Double result_calorie) {
        this.result_calorie = result_calorie;
    }

    @Override
    public String toString() {
        if(isRequestOk()){
            return "DishResult{" +
                    "result_num=" + result_num +
                    ", isDish=" + isDish +
                    ", result_name='" + result_name + '\'' +
                    ", result_probability=" + result_probability +
                    ", result_calorie=" + result_calorie +
                    ", result=" + result +
                    '}';
        }else{
            return getError_msg()+";错误代码["+getError_code()+"]"+";log_id["+getLog_id()+"]";
        }

    }
}
