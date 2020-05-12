package com.ruoyi.third.baidu.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * userDefine  用户自定义审核接口结果
 * @author wujiyue
 */
public class ImageVerifyResult{
    /**
     *结果json字符串
     */
    private String json;
    /**
     *true 合规  false 不合规
     */
    private boolean result;
    /**
     *1.合规,2.疑似，3.不合规
     */
    private int resultType;
    /**
     *描述
     */
    private String description;
    private List<ErrorMsgBean> data;

    public List<ErrorMsgBean> getData() {
        return data;
    }

    public void setData(List<ErrorMsgBean> data) {
        this.data = data;
    }
    public static ImageVerifyResult create(String jsonStr) {
        return new ImageVerifyResult(jsonStr);
    }

    @Override
    public String toString() {
        return "ImageVerifyResult{" +
                "result=" + result +
                ", resultType=" + resultType +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public class ErrorMsgBean{
        /**
         * 违规信息
         */
        private String msg;//
        /**
         * 违规类型代码
         */
        private String type;//
        /**
         * 概率
         */
        private Double probability;//
        /**
         * type=11 存在公众人物  会把人物识别名称和概率列出来   Map内的key有name、probability
         */
        private List<Map> stars;

        @Override
        public String toString() {
            return "ErrorMsgBean{" +
                    "stars=" + stars +
                    ", msg='" + msg + '\'' +
                    ", type='" + type + '\'' +
                    ", probability=" + probability +
                    '}';
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(Double probability) {
            this.probability = probability;
        }

        public List<Map> getStars() {
            return stars;
        }

        public void setStars(List<Map> stars) {
            this.stars = stars;
        }


    }
    public ImageVerifyResult(String json){
        //result:     {"conclusion":"不合规","log_id":152894126780709,"data":[{"msg":"存在水印码内容","probability":0.9978612,"type":5},{"msg":"存在公众人物","stars":[{"probability":0.65225273,"name":"血纯茗雅"}],"type":11}],"conclusionType":2}
        //result:     {"conclusion":"合规","log_id":152894144516161,"conclusionType":1}
        this.json = json;
        try{
            JSONObject o= JSON.parseObject(json);
            String conclusionType= String.valueOf(o.get("conclusionType"));
            String conclusion= String.valueOf(o.get("conclusion"));
            if("1".equals(conclusionType)){
                resultType=1;
                result=true;
                description=conclusion;
            }else if("2".equals(conclusionType)){
                resultType=2;
                result=false;
                description=conclusion;
            }else{
                resultType=3;
                result=false;
                description=conclusion;
            }
            if(!result){
                //不合格，就去获得不合格详细原因
                List<Map> list= JSONArray.parseArray(o.get("data").toString(), Map.class);
                if(list!=null&&list.size()>0){
                    List<ErrorMsgBean> errorMsgBeans=new ArrayList<ErrorMsgBean>();
                    ErrorMsgBean tempBean=null;
                    for(Map tmap:list){
                        tempBean=new ErrorMsgBean();
                        tempBean.setMsg(String.valueOf(tmap.get("msg")));
                        tempBean.setType(String.valueOf(tmap.get("type")));
                        if(tmap.get("probability")!=null){
                            tempBean.setProbability(Double.valueOf(String.valueOf(tmap.get("probability"))));
                        }else{
                            tempBean.setProbability(null);
                        }
                        if(tmap.get("stars")!=null){
                            List<Map> starsList= JSONArray.parseArray(tmap.get("stars").toString(), Map.class);
                            if(starsList!=null&&starsList.size()>0){
                                tempBean.setStars(starsList);
                            }
                        }
                        errorMsgBeans.add(tempBean);
                    }
                    this.data=errorMsgBeans;
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            resultType=-1;
            result=false;
            description="结果解析异常!";
        }

    }
}
