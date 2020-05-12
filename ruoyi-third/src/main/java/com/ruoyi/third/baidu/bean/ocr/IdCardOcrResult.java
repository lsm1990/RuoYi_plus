package com.ruoyi.third.baidu.bean.ocr;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;


/**
 * 身份证识别结果
 * @author
 */
public class IdCardOcrResult extends BaseResult {

    private String image_status;

    private String words_result_num;
    private Integer direction;


    private String name;
    private String ID;
    private String gender;
    private String minZhu;
    private String address;
    private String birthday;
    public IdCardOcrResult(String json){
        this.setJson(json);
        try{
            JSONObject jsonObject= JSONObject.parseObject(json);
            String e_code=jsonObject.getString("error_code");
            if(notNull(e_code)){
                this.setError_code(e_code);
                String e_msg=jsonObject.getString("error_msg");
                this.setError_msg(e_msg);
                this.setRequestOk(false);
            }else {
                this.setRequestOk(true);
                this.setLog_id(jsonObject.getString("log_id"));
                this.image_status=jsonObject.getString("image_status");
                this.words_result_num=jsonObject.getString("words_result_num");
                this.direction=jsonObject.getInteger("direction");
                JSONObject words_result_json=jsonObject.getJSONObject("words_result");
                this.name=words_result_json.getJSONObject("姓名").getString("words");
                this.address=words_result_json.getJSONObject("住址").getString("words");
                this.birthday=words_result_json.getJSONObject("出生").getString("words");
                this.ID=words_result_json.getJSONObject("公民身份号码").getString("words");
                this.gender=words_result_json.getJSONObject("性别").getString("words");
                this.minZhu=words_result_json.getJSONObject("民族").getString("words");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static IdCardOcrResult create(String jsonStr) {
        return new IdCardOcrResult(jsonStr);
    }

    @Override
    public String toString() {
        return "IdCardOcrResult{" +
                "image_status='" + image_status + '\'' +
                ", words_result_num='" + words_result_num + '\'' +
                ", direction=" + direction +
                ", name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", gender='" + gender + '\'' +
                ", minZhu='" + minZhu + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    public String getImage_status() {
        return image_status;
    }

    public void setImage_status(String image_status) {
        this.image_status = image_status;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMinZhu() {
        return minZhu;
    }

    public void setMinZhu(String minZhu) {
        this.minZhu = minZhu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
