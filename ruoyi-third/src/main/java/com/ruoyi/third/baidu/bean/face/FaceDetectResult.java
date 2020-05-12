package com.ruoyi.third.baidu.bean.face;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;
import java.util.List;
import java.util.Map;

/**
 * @author wujiyue
 */
public class FaceDetectResult extends BaseResult {

    private Integer face_num;
    private List<Map> face_list;

    /**
     *如果只有一张人脸 把下面属性填充值方便读取
     */
    private String face_token;
    /**
     * left、top、width、height
     */
    private Map location;
    /**
     * yaw三维旋转之左右旋转角[-90(左), 90(右)] pitch三维旋转之俯仰角度[-90(上), 90(下)] roll平面内旋转角[-180(逆时针), 180(顺时针)]
     */
    private Map angle;
    /**
     * 平面内旋转角[-180(逆时针), 180(顺时针)]  angel.roll
     */
    private Double roll;
    /**
     *年龄
     */
    private Integer age;
    /**
     *颜值
     */
    private Double beauty;
    /**
     *表情
     */
    private String expression;
    /**
     *性别
     */
    private String gender;
    /**
     *民族
     */
    private String race;
    /**
     *是否戴眼镜
     */
    private boolean hasGlasses;



    //{"error_code":0,"error_msg":"SUCCESS","log_id":2810389254,"timestamp":1529551857,"cached":0,"result":{"face_num":1,"face_list":[{"face_token":"40b86d590f87c6e1b177e8b93c6a6376","location":{"left":119.9293823,"top":119.4915924,"width":90,"height":103,"rotation":14},"face_probability":1,"angle":{"yaw":-17.01270103,"pitch":-1.34694922,"roll":10.41974068},"age":22,"beauty":68.86720276,"expression":{"type":"smile","probability":0.974437952},"gender":{"type":"female","probability":0.9999629259},"glasses":{"type":"none","probability":0.9999988079},"race":{"type":"yellow","probability":0.9999338388}}]}}
    public FaceDetectResult(String jsonStr){
        this.setJson(jsonStr);
        try{
            JSONObject jsonObject= JSONObject.parseObject(jsonStr);
            String e_code=String.valueOf(jsonObject.get("error_code"));
            String e_msg=String.valueOf(jsonObject.get("error_msg"));
            this.setLog_id( String.valueOf(jsonObject.get("log_id")));
            if(!"0".equals(e_code)&&!"SUCCESS".equals(e_msg)){
                this.setError_code(e_code);
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else{
                this.setRequestOk(true);
                JSONObject result_json=jsonObject.getJSONObject("result");
                this.face_num=result_json.getInteger("face_num");
                this.face_list = JSONArray.parseArray(result_json.get("face_list").toString(), Map.class);
                if(face_list!=null&&face_list.size()>0){
                    Map tmap = face_list.get(0);
                    if(tmap!=null){
                        this.face_token = (String) tmap.get("face_token");
                        this.location= JSONObject.parseObject(String.valueOf(tmap.get("location")),Map.class);
                        this.angle= JSONObject.parseObject(String.valueOf(tmap.get("angle")),Map.class);
                        this.roll=Double.valueOf(String.valueOf(angle.get("roll")));
                        this.age = (Integer) tmap.get("age");
                        this.beauty =Double.valueOf(String.valueOf(tmap.get("beauty")));
                        this.expression=(String)(JSONObject.parseObject(String.valueOf(tmap.get("expression")),Map.class).get("type"));
                        this.gender=(String)(JSONObject.parseObject(String.valueOf(tmap.get("gender")),Map.class).get("type"));
                        this.race=(String)(JSONObject.parseObject(String.valueOf(tmap.get("race")),Map.class).get("type"));
                        this.hasGlasses="none".equals((String)(JSONObject.parseObject(String.valueOf(tmap.get("glasses")),Map.class).get("type")))?false:true;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static FaceDetectResult create(String jsonStr) {
        return new FaceDetectResult(jsonStr);
    }

    public Integer getFace_num() {
        return face_num;
    }

    public void setFace_num(Integer face_num) {
        this.face_num = face_num;
    }

    public List<Map> getFace_list() {
        return face_list;
    }

    public void setFace_list(List<Map> face_list) {
        this.face_list = face_list;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public Map getLocation() {
        return location;
    }

    public void setLocation(Map location) {
        this.location = location;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBeauty() {
        return beauty;
    }

    public void setBeauty(Double beauty) {
        this.beauty = beauty;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public boolean isHasGlasses() {
        return hasGlasses;
    }

    public void setHasGlasses(boolean hasGlasses) {
        this.hasGlasses = hasGlasses;
    }

    public Map getAngle() {
        return angle;
    }

    public void setAngle(Map angle) {
        this.angle = angle;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "FaceDetectResult{" +
                "face_num=" + face_num +
                ", face_list=" + face_list +
                ", face_token='" + face_token + '\'' +
                ", location=" + location +
                ", angle=" + angle +
                ", roll=" + roll +
                ", age=" + age +
                ", beauty=" + beauty +
                ", expression='" + expression + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", hasGlasses=" + hasGlasses +
                '}';
    }
}
