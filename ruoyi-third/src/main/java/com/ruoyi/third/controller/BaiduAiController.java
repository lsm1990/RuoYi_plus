package com.ruoyi.third.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.third.baidu.api.BaiduAi;
import com.ruoyi.third.baidu.bean.face.FaceDetectResult;
import com.ruoyi.third.baidu.bean.imgClassify.AnimalResult;
import com.ruoyi.third.baidu.bean.imgClassify.CarResult;
import com.ruoyi.third.baidu.bean.imgClassify.DishResult;
import com.ruoyi.third.baidu.bean.imgClassify.PlantResult;
import com.ruoyi.third.baidu.bean.ocr.*;
import com.ruoyi.third.baidu.service.FaceService;
import com.ruoyi.third.baidu.service.ImageClassifyService;
import com.ruoyi.third.baidu.service.OcrService;
import com.ruoyi.third.domain.AiHis;
import com.ruoyi.third.service.IAiHisService;
import com.sun.jna.platform.win32.Guid;
import org.apache.commons.fileupload.util.Streams;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wujiyue
 */
@Controller
@RequestMapping("/third/ai")
public class BaiduAiController extends BaseController {

    @Autowired
    IAiHisService aiHisService;
    private static String prefix="/third/ai/";
    public static String DEFAULT_UPLOAD_BASE_PATH=FileUploadUtils.getDefaultBaseDir()+"/temp";

    @RequestMapping(value={"","/"})
    @RequiresPermissions("third:ai")
    public String index(){
        return prefix+"/baiduAi";
    }

    //上传单张图片资源
    @ResponseBody
    @RequestMapping("/queryAi")
    public Object upload(@RequestParam("type") String aiType,@RequestParam("file") MultipartFile multipartFile) throws Exception{
        Map resultMap =new HashMap<String,Object>();
        if(StringUtils.isEmpty(aiType)){
            return AjaxResult.error("type参数不能为空!");
        }
        String path=FileUploadUtils.upload(DEFAULT_UPLOAD_BASE_PATH,multipartFile);
        resultMap.put("path",path);
        //去掉映射路径前缀
        path= path.replace(Constants.RESOURCE_PREFIX,"");
        String localFilePath=Global.getProfile()+path;
        return queryAi(aiType,localFilePath);
    }
    public Object queryAi(String aiType,String localFilePath){

        String json="";//返回最原始的json
        String aiName="";
        if(BaiduAi.AiType.faceDetect.name().equals(aiType)){
            FaceDetectResult result = FaceService.faceDetect_localPath(localFilePath);
            json=result.getJson();
            aiName="人脸检测";
        }else if(BaiduAi.AiType.plant.name().equals(aiType)){
            PlantResult result= ImageClassifyService.plant(localFilePath);
            json=result.getJson();
            aiName="植物识别";
        }else if(BaiduAi.AiType.bankCard.name().equals(aiType)){
            BankCardOcrResult result = OcrService.bankCardOcr(localFilePath);
            json=result.getJson();
            aiName="银行卡识别";
        }else if(BaiduAi.AiType.idCard.name().equals(aiType)){
            IdCardOcrResult result = OcrService.idcardOcr_Z(localFilePath);
            json=result.getJson();
            aiName="身份证识别";
        }else if(BaiduAi.AiType.plate.name().equals(aiType)){
            //识别车牌号
            PlateNumberResult result = OcrService.license_plateOcr(localFilePath);
            json=result.getJson();
            aiName="识别车牌号";
        }else if(BaiduAi.AiType.driver.name().equals(aiType)){
            //驾驶证识别
            aiName="驾驶证识别";
            DriveLicenseOcrResult result = OcrService.drivingLicenseOcr(localFilePath);
            json=result.getJson();
        }else if(BaiduAi.AiType.animal.name().equals(aiType)){
            //动物识别
            AnimalResult result = ImageClassifyService.animal(localFilePath);
            json=result.getJson();
        }else if(BaiduAi.AiType.car.name().equals(aiType)){
            //车型识别
            aiName="车型识别";
            CarResult result = ImageClassifyService.car(localFilePath);
            json=result.getJson();
        }else if(BaiduAi.AiType.dish.name().equals(aiType)){
            //菜品识别
            aiName="菜品识别";
            DishResult result = ImageClassifyService.dish(localFilePath);
            json=result.getJson();
        }else if(BaiduAi.AiType.general_basic.name().toString().equals(aiType)){
            //通用文字识别
            aiName="通用文字识别";
            GeneralBasicIOcrResult result= OcrService.general_basic(localFilePath);
            json=result.getJson();
        }
        AiHis aiHis=new AiHis();
        JSONObject jsonObject= JSONObject.parseObject(json);
        String e_code=String.valueOf(jsonObject.get("error_code"));
        String e_msg=String.valueOf(jsonObject.get("error_msg"));
        String log_id=String.valueOf(jsonObject.get("log_id"));
        aiHis.setId(log_id);
        if(StringUtils.isNotEmpty(e_code)&&!"0".equals(e_code)){
            aiHis.setResult("0");
        }else{
            aiHis.setResult("1");
        }
        if(StringUtils.isNotEmpty(e_msg)){
            aiHis.setErrorMsg(e_msg);
        }
        aiHis.setAiType(aiType);
        aiHis.setTypeName(aiName);
        aiHis.setJsonResult(json);
        SysUser user=ShiroUtils.getSysUser();
        aiHis.setYhid(String.valueOf(user.getUserId()));
        aiHis.setYhmc(user.getUserName());
        aiHisService.insertAiHis(aiHis);
        return AjaxResult.success("",json);
    }
    public Object queryAi2(String aiType,String localFilePath,Map resultMap){
        if(BaiduAi.AiType.faceDetect.name().equals(aiType)){
            FaceDetectResult result = FaceService.faceDetect_localPath(localFilePath);
            if(result.isRequestOk()){
                resultMap.put("result","成功");
                resultMap.put("age",result.getAge());
                resultMap.put("gender","female".equals(result.getGender())?"女":"男");
                resultMap.put("beauty",result.getBeauty());
                resultMap.put("expression",result.getExpression());
                resultMap.put("hasGlasses",result.isHasGlasses()?"有":"无");
            }else{
                resultMap.put("result","失败"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.plant.name().equals(aiType)){
            PlantResult result= ImageClassifyService.plant(localFilePath);
            if(result.isRequestOk()){
                //请求成功
                if(result.isPlant()){
                    //确定是植物
                    resultMap.put("result","成功");
                    resultMap.put("name",result.getResult_name());
                    resultMap.put("probability",result.getResult_probability());
                }else{
                    //不能判定是否植物
                    resultMap.put("result","非植物");
                    resultMap.put("resultJson", JSON.toJSONString(result.getResult()));
                }
            }else{
                //请求失败
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.bankCard.name().equals(aiType)){
            BankCardOcrResult result = OcrService.bankCardOcr(localFilePath);
            if(result.isRequestOk()){
                resultMap.put("result","成功");
                resultMap.put("cardNum",result.getBank_card_number());
                resultMap.put("bankName",result.getBank_name());
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.idCard.name().equals(aiType)){
            IdCardOcrResult result = OcrService.idcardOcr_Z(localFilePath);
            if(result.isRequestOk()){
                resultMap.put("result","成功");
                resultMap.put("gender",result.getGender());
                resultMap.put("address",result.getAddress());
                resultMap.put("birthday",result.getBirthday());
                resultMap.put("ID",result.getID());
                resultMap.put("name",result.getName());
                resultMap.put("minzhu",result.getMinZhu());
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.plate.name().equals(aiType)){
            //识别车牌号
            PlateNumberResult result = OcrService.license_plateOcr(localFilePath);
            if(result.isRequestOk()){
                resultMap.put("result","成功");
                resultMap.put("number",result.getPlateNumber());
                resultMap.put("color",result.getColor());
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.driver.name().equals(aiType)){
            //驾驶证识别
            DriveLicenseOcrResult result = OcrService.drivingLicenseOcr(localFilePath);
            if(result.isRequestOk()){
                resultMap.put("result","成功");
                resultMap.put("ID",result.getID());
                resultMap.put("name",result.getName());
                resultMap.put("gender",result.getGender());
                resultMap.put("address",result.getAddress());
                resultMap.put("birthday",result.getBirthday());
                resultMap.put("country",result.getCountry());
                resultMap.put("time",result.getStarttime()+"-"+result.getEndtime());
                resultMap.put("type",result.getType());//C1
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.animal.name().equals(aiType)){
            //动物识别
            AnimalResult result = ImageClassifyService.animal(localFilePath);
            if(result.isRequestOk()){
                if(result.isAnimal()){
                    resultMap.put("result","成功");
                    resultMap.put("name",result.getResult_name());
                    resultMap.put("probability",result.getResult_probability());
                }else{
                    resultMap.put("result","非动物");
                    resultMap.put("resultJson", JSON.toJSONString(result.getResult()));
                }
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.car.name().equals(aiType)){
            //车型识别
            CarResult result = ImageClassifyService.car(localFilePath);
            if(result.isRequestOk()){
                if(result.isCar()){
                    resultMap.put("result","成功");
                    resultMap.put("name",result.getResult_name());
                    resultMap.put("probability",result.getResult_probability());
                    resultMap.put("year",result.getResult_year());
                }else{
                    resultMap.put("result","非车辆");
                    resultMap.put("resultJson", JSON.toJSONString(result.getResult()));
                }
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.dish.name().equals(aiType)){
            //菜品识别
            DishResult result = ImageClassifyService.dish(localFilePath);
            if(result.isRequestOk()){
                if(result.isDish()){
                    resultMap.put("result","成功");
                    resultMap.put("name",result.getResult_name());
                    resultMap.put("probability",result.getResult_probability());
                    resultMap.put("calorie",result.getResult_calorie());
                }else{
                    resultMap.put("result","非菜");
                    resultMap.put("resultJson", JSON.toJSONString(result.getResult()));
                }
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }else if(BaiduAi.AiType.general_basic.name().toString().equals(aiType)){
            //通用文字识别
            GeneralBasicIOcrResult result= OcrService.general_basic(localFilePath);
            if(result.isRequestOk()){
                resultMap.put("result","成功");
                resultMap.put("words_result_num",result.getWords_result_num());
                resultMap.put("words",result.getWords());
                resultMap.put("paragraph",result.getParagraph());
            }else{
                resultMap.put("result","识别失败!"+result.getError_msg());
            }
        }
        return resultMap;
    }
}
