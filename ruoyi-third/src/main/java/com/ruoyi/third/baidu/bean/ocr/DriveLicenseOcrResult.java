package com.ruoyi.third.baidu.bean.ocr;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;


/**
 * 驾驶证识别
 * @author wujiyue
 */
public class DriveLicenseOcrResult extends BaseResult {


    private String words_result_num;
    private Integer direction;

    private String type;//准假车型 C1
    private String ID;//证号
    private String starttime;//有效期限 始
    private String endtime;//有效期限 止
    private String name;//
    private String birthday;
    private String country;
    private String gender;
    private String firstTime;
    private String address;

    public DriveLicenseOcrResult(String json){
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
                this.words_result_num=jsonObject.getString("words_result_num");
                this.direction=jsonObject.getInteger("direction");
                JSONObject words_result_json=jsonObject.getJSONObject("words_result");
                this.type = words_result_json.getJSONObject("准驾车型").getString("words");
                this.name = words_result_json.getJSONObject("姓名").getString("words");
                this.starttime=words_result_json.getJSONObject("有效期限").getString("words");
                this.endtime=words_result_json.getJSONObject("至").getString("words");
                this.birthday=words_result_json.getJSONObject("出生日期").getString("words");
                this.ID=words_result_json.getJSONObject("证号").getString("words");
                this.gender=words_result_json.getJSONObject("性别").getString("words");
                this.country=words_result_json.getJSONObject("国籍").getString("words");
                this.firstTime=words_result_json.getJSONObject("初次领证日期").getString("words");
                this.address=words_result_json.getJSONObject("住址").getString("words");


            }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static DriveLicenseOcrResult create(String jsonStr) {
        return new DriveLicenseOcrResult(jsonStr);
    }

    @Override
    public String toString() {
        return "DriveLicenseOcrResult{" +
                "words_result_num='" + words_result_num + '\'' +
                ", direction=" + direction +
                ", type='" + type + '\'' +
                ", ID='" + ID + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", firstTime='" + firstTime + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }
}
