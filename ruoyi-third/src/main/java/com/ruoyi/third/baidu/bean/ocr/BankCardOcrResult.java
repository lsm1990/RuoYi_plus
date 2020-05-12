package com.ruoyi.third.baidu.bean.ocr;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaseResult;


/**
 *  银行卡识别
 * @author wujiyue
 */
public class BankCardOcrResult extends BaseResult {
    private String bank_name;
    private Integer bank_card_type;
    private String bank_card_number;

    public BankCardOcrResult(String json){
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
                JSONObject res=jsonObject.getJSONObject("result");
                this.bank_name=res.getString("bank_name");
                this.bank_card_number=res.getString("bank_card_number");
                this.bank_card_type=res.getInteger("bank_card_type");
         }
        }catch (Exception ex){
            ex.printStackTrace();
            this.setRequestOk(false);
            this.setError_msg("解析json字符串出现异常!");
        }
    }
    public static BankCardOcrResult create(String jsonStr) {
        return new BankCardOcrResult(jsonStr);
    }

    @Override
    public String toString() {
        return "BankCardOcrResult{" +
                "bank_name='" + bank_name + '\'' +
                ", bank_card_type=" + bank_card_type +
                ", bank_card_number='" + bank_card_number + '\'' +
                '}';
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public Integer getBank_card_type() {
        return bank_card_type;
    }

    public void setBank_card_type(Integer bank_card_type) {
        this.bank_card_type = bank_card_type;
    }

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }
}
