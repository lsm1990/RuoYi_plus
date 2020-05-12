package com.ruoyi.third.baidu.bean.imgClassify;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.List;
import java.util.Map;

/**
 * 植物识别结果
 * @author wujiyue
 */
public class PlantResult extends BaseResult {

    //{"log_id": 2269977770483958686, "result": [{"score": 0.99263727664948, "name": "月季花"}, {"score": 0.0021303170360625, "name": "香水月季"}, {"score": 0.0013882109196857, "name": "玫瑰"}, {"score": 0.00088617182336748, "name": "丰花月季"}, {"score": 0.00086312287021428, "name": "粉和平月季"}]}
    private boolean isPlant;
    /**
     * 认定为是植物的阈值概率
     */
    private Double plantProbability=0.7;
    /**
     *根据阈值概率最总认定为是那个植物名称
     */
    private String result_name;
    /**
     *定为是那个植物的概率
     */
    private Double result_probability;
    private List<Map> result;

    public PlantResult(String json){
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
                        if((list.size()==1&&"非植物".equals(name))||(pro>=0.5 && "非植物".equals(name))){
                            isPlant=false;
                            break;
                        }
                        if(pro>plantProbability){
                            isPlant=true;
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
    public static PlantResult create(String jsonStr) {
        return new PlantResult(jsonStr);
    }

    @Override
    public String toString() {
        if(isRequestOk()){
            return "PlantResult{" +
                    "isPlant=" + isPlant +
                    ", result_name='" + result_name + '\'' +
                    ", result_probability=" + result_probability +
                    ", result=" + result +
                    '}';
        }else{
            return getError_msg()+";错误代码["+getError_code()+"]"+";log_id["+getLog_id()+"]";
        }
    }

    public boolean isPlant() {
        return isPlant;
    }

    public void setPlant(boolean isPlant) {
        this.isPlant = isPlant;
    }

    public Double getPlantProbability() {
        return plantProbability;
    }

    public void setPlantProbability(Double plantProbability) {
        this.plantProbability = plantProbability;
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
}
