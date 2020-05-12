package com.ruoyi.third.baidu.bean.imgCensor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网络图片文字识别 返回结果
 * @author wujiyue
 */
public class OcrResult extends BaseResult {

    private Integer words_result_num;
    private Integer direction;
    private List<String> words;
    public OcrResult(String jsonStr){
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
                this.setLog_id(jsonObject.getString("log_id"));
                Integer num=Integer.valueOf(String.valueOf(jsonObject.get("words_result_num")));
                this.words_result_num=num;
                Integer dir = Integer.valueOf(String.valueOf(jsonObject.get("direction")));
                this.direction=dir;
                List<Map> wordList = JSONArray.parseArray(jsonObject.get("words_result").toString(),Map.class);
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
    public static OcrResult create(String jsonStr) {
        return new OcrResult(jsonStr);
    }

    @Override
    public String toString() {
        return "OcrResult{" +
                super.toString() +
                "words_result_num=" + words_result_num +
                ", direction=" + direction +
                ", words=" + words +
                '}';
    }

    public Integer getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(Integer words_result_num) {
        this.words_result_num = words_result_num;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

}
