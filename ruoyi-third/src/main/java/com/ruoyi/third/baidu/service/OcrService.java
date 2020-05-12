package com.ruoyi.third.baidu.service;


import com.ruoyi.third.baidu.api.OcrAPI;
import com.ruoyi.third.baidu.bean.ocr.*;

/**
 * @author wujiyue
 */
public class OcrService  {

    /**
     * 识别正面身份证信息
     * @param path
     * @return
     */
    public static IdCardOcrResult idcardOcr_Z(String path){
       String json = OcrAPI.idcardOcr(path, false);
        return IdCardOcrResult.create(json);
    }
    /**
     * 识别反面身份证信息
     * @param path
     * @return
     */
    public static IdCardOcrResult idcardOcr_F(String path){
        String json = OcrAPI.idcardOcr(path,true);
        return IdCardOcrResult.create(json);
    }

    /**
     * 识别银行卡信息
     * @param path
     * @return
     */
    public static BankCardOcrResult bankCardOcr(String path){
        String json = OcrAPI.bankCardOcr(path);
        return BankCardOcrResult.create(json);
    }
    /**
     * 识别驾驶证
     * @param path
     * @return
     */
    public static DriveLicenseOcrResult drivingLicenseOcr(String path){
        String json=OcrAPI.drivingLicenseOcr(path);
        return  DriveLicenseOcrResult.create(json);
    }

    /**
     * 识别车牌号
     * @param path
     * @return
     */
    public static PlateNumberResult license_plateOcr(String path){
        String json=OcrAPI.license_plateOcr(path);
        return  PlateNumberResult.create(json);
    }

    /**
     * 通用文字识别
     * @param path
     * @return
     */
    public static GeneralBasicIOcrResult general_basic(String path){
        String json=OcrAPI.general_basic(path);
        return  GeneralBasicIOcrResult.create(json);
    }

    public static void main(String[] args){
        /*String path="C:\\Users\\Administrator\\Desktop\\id.jpg";
        IdCardOcrResult result =idcardOcr_Z(path);
        System.out.println(result);*/
       /* String path="C:\\Users\\Administrator\\Desktop\\bank.jpg";
        BankCardOcrResult s=bankCardOcr(path);
        System.out.println(s);*/
        /*String path="C:\\Users\\Administrator\\Desktop\\drive.jpeg";
        DriveLicenseOcrResult s=drivingLicenseOcr(path);
        System.out.println(s);*/

        String path="C:\\Users\\Administrator\\Desktop\\plate.png";
        PlateNumberResult s=license_plateOcr(path);
        System.out.println(s);

    }
}
