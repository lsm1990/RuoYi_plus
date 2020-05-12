package com.ruoyi.spider.fast;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.domain.SpiderException;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 通过读取json配置文件快速构建爬虫任务配置信息
 */
public class FastConfigTemplate {

    public static JSONObject configTemplate;

    static {
        FastConfigTemplate configTemplate = new FastConfigTemplate();
        configTemplate.init();
    }

    public static String getConfig(String code) {
        if (configTemplate.containsKey(code)) {
            return configTemplate.getString(code);
        }
        throw new SpiderException("不存在的配置代码[" + code + "]!");
    }

    private void init() {
        String configFileName = SpiderConstants.CONFIG_FILE_NAME;
        String config = null;
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(configFileName);
            if (null == inputStream) {
                throw new SpiderException("请检查`src/main/resources`下是否存在" + configFileName);
            }
            config = IoUtil.read(inputStream, Charset.forName("UTF-8"));
            if (StringUtils.isEmpty(config)) {
                throw new SpiderException("HunterConfig内容为空：" + configFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            configTemplate = JSONObject.parseObject(config);
        } catch (Exception e) {
            throw new SpiderException("HunterConfig配置文件格式错误");
        }

    }

}
