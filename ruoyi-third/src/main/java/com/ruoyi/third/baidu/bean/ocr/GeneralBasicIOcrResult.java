package com.ruoyi.third.baidu.bean.ocr;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用文字识别结果
 * @author wujiyue
 */
public class GeneralBasicIOcrResult  extends BaseResult {

    private int words_result_num;

    private List<String> words;
    private String paragraph;//上面的words组成的一整段文字
    public GeneralBasicIOcrResult(String jsonStr){
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
                StringBuffer sb=new StringBuffer();
                words.forEach(s->{
                    sb.append(s);

                });
                this.paragraph=sb.toString();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static GeneralBasicIOcrResult create(String jsonStr) {
        return new GeneralBasicIOcrResult(jsonStr);
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public String toString() {
        return "GeneralBasicIOcrResult{" +
                "words_result_num=" + words_result_num +
                ", words=" + words +
                ", paragraph=" + paragraph +
                '}';
    }
}
