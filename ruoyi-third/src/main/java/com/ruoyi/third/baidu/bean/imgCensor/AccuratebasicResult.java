package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wujiyue
 */
public class AccuratebasicResult extends BaseResult {

    private Integer words_result_num;
    private List<String> words;
    public AccuratebasicResult(String jsonStr){
        this.setJson(jsonStr);
        try{
            JSONObject jsonObject= JSONObject.parseObject(jsonStr);
            String e_code=String.valueOf(jsonObject.get("error_code"));
            if(notNull(e_code)){
                this.setError_code(e_code);
                String e_msg=String.valueOf(jsonObject.get("error_msg"));
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else{
                this.setRequestOk(true);
                this.words_result_num=jsonObject.getInteger("words_result_num");
                List<Map> wordList = JSONArray.parseArray(jsonObject.get("words_result").toString(), Map.class);
                if(wordList!=null&&wordList.size()>0){
                    List<String> ls=new ArrayList<String>();
                    for(Map tm:wordList){
                        ls.add(String.valueOf(tm.get("words")));
                    }
                    this.words=ls;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static AccuratebasicResult create(String jsonStr) {
        return new AccuratebasicResult(jsonStr);
    }
    public Integer getWords_result_num() {
        return words_result_num;
    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return "AccuratebasicResult{" +
                "words_result_num=" + words_result_num +
                ", words=" + words +
                '}';
    }


}
