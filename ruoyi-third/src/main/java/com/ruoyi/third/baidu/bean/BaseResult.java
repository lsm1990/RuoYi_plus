package com.ruoyi.third.baidu.bean;

import java.io.Serializable;

/**
 * @author wujiyue
 */
public class BaseResult implements Serializable {

    private String json;//结果json字符串
    private String log_id;//请求标识码，随机数，唯一
    private String error_msg;//
    private String error_code;//
    private boolean requestOk;//请求成功
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "log_id='" + log_id + '\'' +
                ", error_msg='" + error_msg + '\'' +
                ", error_code='" + error_code + '\'' +
                ", requestOk=" + requestOk +
                '}';
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public boolean isRequestOk() {
        return requestOk;
    }

    public void setRequestOk(boolean requestOk) {
        this.requestOk = requestOk;
    }

    public boolean notNull(String v){
        return v!=null&&!"".equals(v)&&!"null".equals(v);
    }
}
