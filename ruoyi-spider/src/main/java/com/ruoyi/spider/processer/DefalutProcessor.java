package com.ruoyi.spider.processer;

import com.ruoyi.spider.MyConfigurableSpider;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.domain.SpiderField;
import com.ruoyi.spider.downloader.HttpClientDownloader;
import com.ruoyi.spider.scheduler.CountDownScheduler;
import org.apache.commons.collections.CollectionUtils;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 爬虫入口
 */
public class DefalutProcessor extends AbstractProcessor {

    public DefalutProcessor(SpiderConfig config) {
        super(config);
    }

    public DefalutProcessor(SpiderConfig config, String uuid) {
        super(config, uuid);
    }

    /**
     * 运行爬虫并返回结果
     *
     * @return
     */
    @Override
    public CopyOnWriteArrayList<LinkedHashMap<String, String>> execute() {
        List<String> errors = this.validateModel(config);
        if (CollectionUtils.isNotEmpty(errors)) {
            logger.warn("校验不通过！请依据下方提示，检查输入参数是否正确......");
            for (String error : errors) {
                logger.warn(">> " + error);
            }
            return null;
        }
        List<SpiderField> fields = config.getFieldsList();
        if(CollectionUtils.isEmpty(fields)){
            logger.warn("校验不通过！爬虫字段对应规则未配置!!!");
            return null;
        }

        CopyOnWriteArrayList<LinkedHashMap<String, String>> datas = new CopyOnWriteArrayList<>();
        MyConfigurableSpider spider = MyConfigurableSpider.create(this, config, uuid);

        spider.addUrl(config.getEntryUrlsList().toArray(new String[0]))
                .setScheduler(new CountDownScheduler(config))
                .setPipelines(config.getPipelineList())
                .addPipeline((resultItems, task) -> this.processData(resultItems, datas, spider)); // 收集数据并返回

            /*if("cms_article".equals(config.getTableName())){
                ArticlePipeline articlePipeline=new ArticlePipeline();
                spider.addPipeline(articlePipeline);
            }else if("cms_book".equals(config.getTableName())){

            }else{
            }*/
            spider.setDownloader(new HttpClientDownloader())
                .thread(config.getThreadCount().intValue());
        if(config.getShowLog()==1){
            spider.addPipeline(new ConsolePipeline());
        }
        //设置抓取代理IP
        if (config.getUseProxy()==1 && !CollectionUtils.isEmpty(config.getProxyList())) {
            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
            SimpleProxyProvider provider = SimpleProxyProvider.from(config.getProxyList().toArray(new Proxy[0]));
            httpClientDownloader.setProxyProvider(provider);
            spider.setDownloader(httpClientDownloader);
        }
        // 测试代理
        /*HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        SimpleProxyProvider provider = SimpleProxyProvider.from(
                new Proxy("61.135.217.7", 80)
        );
        httpClientDownloader.setProxyProvider(provider);
        spider.setDownloader(httpClientDownloader);*/

        // 启动爬虫
        spider.run();
        return datas;
    }


}
