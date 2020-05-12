package com.ruoyi.third.baidu.bean.imgClassify;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * 动物识别结果
 * @author wujiyue
 */
public class AnimalResult extends BaseResult {

    //{"log_id": 4413670068622107758, "result": [{"score": "0.663324", "name": "金吉拉猫"}, {"score": "0.113101", "name": "布偶猫"}]}
    //{"log_id": 55148098712447112, "result": [{"score": "0.999139", "name": "非动物"}]}
    private boolean isAnimal;
    //认定为是哪个动物的阈值概率
    private Double animalProbability=0.7;
    //根据阈值概率最总认定为是那个动物名称
    private String result_name;
    //定为是那个动物的概率
    private Double result_probability;
    private List<Map> result;

    public AnimalResult(String json){
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

                List<Map> list = JSONArray.parseArray(jsonObject.get("result").toString(), Map.class);
                if(list!=null&&list.size()>0){
                    for(Map t:list){
                        String name=String.valueOf(t.get("name"));
                        Double pro=Double.valueOf(String.valueOf(t.get("score")));
                        if((list.size()==1&&"非动物".equals(name))||(pro>=0.5 && "非动物".equals(name))){
                            isAnimal=false;
                            this.result_name=name;
                            this.result_probability=pro;
                            break;
                        }
                        if(pro>animalProbability){
                            isAnimal=true;
                            this.result_name=name;
                            this.result_probability=pro;

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
    public static AnimalResult create(String jsonStr) {
        return new AnimalResult(jsonStr);
    }

    public boolean isAnimal() {
        return isAnimal;
    }

    public void setAnimal(boolean isAnimal) {
        this.isAnimal = isAnimal;
    }

    public Double getAnimalProbability() {
        return animalProbability;
    }

    public void setAnimalProbability(Double animalProbability) {
        this.animalProbability = animalProbability;
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

    public List<Map> getResult() {
        return result;
    }

    public void setResult(List<Map> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        if(isRequestOk()){
            return "AnimalResult{" +
                    "isAnimal=" + isAnimal +
                    ", result_name='" + result_name + '\'' +
                    ", result_probability=" + result_probability +
                    ", result=" + result +
                    '}';
        }else{
            return getError_msg()+";错误代码["+getError_code()+"]"+";log_id["+getLog_id()+"]";
        }
    }


}
