package com.ruoyi.third.baidu.bean.imgClassify;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;

import java.util.Map;

/**
 * 主体对象检测
 * @author wujiyue
 */
public class ObjectDetectResult extends BaseResult {

    private Map location;
    private Integer width;
    private Integer height;
    private Integer left;
    private Integer top;

    public ObjectDetectResult(String json){
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
                this.location = JSONObject.parseObject(jsonObject.get("result").toString(), Map.class);
                if(this.location!=null) {
                    this.setRequestOk(true);
                    this.width=Integer.valueOf(String.valueOf(location.get("width")));
                    this.height=Integer.valueOf(String.valueOf(location.get("height")));
                    this.left=Integer.valueOf(String.valueOf(location.get("left")));
                    this.top=Integer.valueOf(String.valueOf(location.get("top")));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static ObjectDetectResult create(String jsonStr) {
        return new ObjectDetectResult(jsonStr);
    }

    public Map getLocation() {
        return location;
    }

    public void setLocation(Map location) {
        this.location = location;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Override
    public String toString() {
        if(isRequestOk()){
            return "ObjectDetectResult{" +
                    "location=" + location +
                    ", width=" + width +
                    ", height=" + height +
                    ", left=" + left +
                    ", top=" + top +
                    '}';
        }else{
            return getError_msg()+";错误代码["+getError_code()+"]"+";log_id["+getLog_id()+"]";
        }

    }
}
