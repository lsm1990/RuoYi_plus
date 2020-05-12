package com.ruoyi.spider.fast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.domain.SpiderField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通过读取json配置文件快速构建爬虫任务配置信息
 */
public class FastConfigContext {

    public static SpiderConfig parseConfig(FastConfigEnum fastConfigEnum) {
        return  parseConfig(fastConfigEnum.getCode());
    }

    /**
     * 解析爬虫配置
     *
     * @param code 爬虫代码
     * @return SpiderConfig
     */
    public static SpiderConfig parseConfig(String code) {
        String platformConfig = FastConfigTemplate.getConfig(code);
        JSONObject jsonObject = JSONObject.parseObject(platformConfig);
        String br = "\r\n";
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        List<SpiderField> spiderFields= Lists.newArrayList();
        String cookieStr ="";
        for (Map.Entry<String, Object> entry : entries) {

            if ("header".equals(entry.getKey())) {
                List<String> headers = JSONArray.parseArray(String.valueOf(entry.getValue()), String.class);
                entry.setValue(String.join(br, headers));
            }
            if ("entryUrls".equals(entry.getKey())) {
                List<String> urls = JSONArray.parseArray(String.valueOf(entry.getValue()), String.class);
                entry.setValue(String.join(",", urls));
            }

            if ("fields".equals(entry.getKey())) {
                List<String> fields = JSONArray.parseArray(String.valueOf(entry.getValue()), String.class);
                SpiderField spiderField=null;
                for(String f:fields){
                    if(f.contains("#")){
                        String[] arr=f.split("#");
                        if(arr!=null&&arr.length==3){
                            spiderField=new SpiderField();
                            spiderField.setFieldName(arr[0].trim());
                            spiderField.setField(arr[1].trim());
                            spiderField.setExtractBy(arr[2].trim());
                            spiderFields.add(spiderField);
                        }
                    }
                }
            }
        }
        SpiderConfig spiderConfig=JSONObject.toJavaObject(jsonObject, SpiderConfig.class);
        spiderConfig.setSpiderCode(code);
        spiderConfig.setFieldsList(spiderFields);
        return spiderConfig;
    }

    /**
     * 重新解析配置模板， 将用户id替换为真实的id
     *
     * @param config config
     * @return config
     */
    public static SpiderConfig replaceConfigUid(SpiderConfig config) {
        if (null == config) {
            return null;
        }
        String uid = config.getUserId();
        if (StringUtils.isEmpty(uid)) {
            return config;
        }
        String domain = config.getDomain();
        if (StringUtils.isNotEmpty(domain)) {
            config.setDomain(domain.replace("{userId}", uid));
        }
        String targetLinksRegex = config.getTargetRegex();
        if (StringUtils.isNotEmpty(targetLinksRegex)) {
            config.setTargetRegex(targetLinksRegex.replace("{userId}", uid));
        }
        //注意入口变量有2个  entryUrlsList 和 entryUrls
        List<String> entryUrlsList = config.getEntryUrlsList();
        if (CollectionUtils.isNotEmpty(entryUrlsList)) {
            List<String> newEntryUrls = new ArrayList<>();
            for (String entryUrl : entryUrlsList) {
                newEntryUrls.add(entryUrl.replace("{userId}", uid));
            }
            config.setEntryUrlsList(newEntryUrls);
        }
        //entryUrls
        String entryUrls=config.getEntryUrls();
        config.setEntryUrls(entryUrls.replace("{userId}", uid));

        Map<String, String> header = config.getHeaders();
        if (MapUtils.isNotEmpty(header)) {
            Set<Map.Entry<String, String>> entries = header.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
                header.put(key, value.replace("{userId}", uid));
            }
        }
        return config;
    }
}
