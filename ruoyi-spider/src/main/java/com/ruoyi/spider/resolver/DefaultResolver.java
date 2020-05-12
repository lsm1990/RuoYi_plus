package com.ruoyi.spider.resolver;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.domain.SpiderField;
import com.ruoyi.spider.domain.SpiderFiledRule;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 解析处理普通的Html网页
 */
public class DefaultResolver implements Resolver {

    @Override
    public void process(Page page, SpiderConfig spiderConfig) {
        List<SpiderField> fields = spiderConfig.getFieldsList();

        Html pageHtml = page.getHtml();
        Document pageDoc =page.getHtml().getDocument();
        String link = page.getRequest().getUrl();

        if(!spiderConfig.getEntryUrlsList().contains(link) || Pattern.matches(spiderConfig.getTargetRegex(), link)) {
            //入口页面无需取值，入口页面只是用于发现目标url，除非入口页面匹配目标url的正则
            for (SpiderField field : fields) {
                String column=field.getField();//有可能取得同一个字段需要多个不同规则
                //先判断该字段是否有值，如果没有在继续处理，有了就不用处理了
                String checkVal = String.valueOf(page.getResultItems().getAll().get(column));
                if(StringUtils.isNotEmpty(checkVal)){
                    continue;
                }
                String type = field.getExtractType();
                if (StringUtils.isEmpty(type)||type.equals(SpiderConstants.FIELD_EXTRACT_TYPE_XPATH)) {
                    this.xpath_put(page, pageHtml, field);
                } else if (type.equals(SpiderConstants.FIELD_EXTRACT_TYPE_CSS)) {
                    this.css_put(page, pageDoc, field);
                } else if (type.equals(SpiderConstants.FIELD_EXTRACT_TYPE_CONSTANT)) {
                    this.constant_put(page,field);
                } else {
                }
            }
            page.putField("link", link);
        }
        if (StringUtils.isNotEmpty(spiderConfig.getTargetRegex())) {
            if(spiderConfig.getCascade()==1 || spiderConfig.getEntryUrlsList().contains(link)){
                //级联发现或者是入口URL才收集目标URL
                page.addTargetRequests(page.getHtml().links().regex(spiderConfig.getTargetRegex()).all());
            }
        }
    }

    /**
     * 处理爬取后原始的文本内容，比如替换截取等操作最终得到自己想要的字符串
     * @param sourceValue
     * @param field
     * @return
     */
    private String processValue(String sourceValue,SpiderField field){
        if(StringUtils.isEmpty(sourceValue) || CollectionUtils.isEmpty(field.getFieldRules())){
            return sourceValue;
        }
        List<SpiderFiledRule> rules = field.getFieldRules();
        rules=rules.stream().sorted(Comparator.comparing(SpiderFiledRule::getSort)).collect(Collectors.toList());
        for(SpiderFiledRule rule:rules){
            if(SpiderConstants.FIELD_PROCESS_TYPE_REPLACE.equals(rule.getProcessType())){
                sourceValue=sourceValue.replace(rule.getReplacereg(),StringUtils.isEmpty(rule.getReplacement())?"":rule.getReplacement());
            }else if(SpiderConstants.FIELD_PROCESS_TYPE_SUBSTRING_AFTER.equals(rule.getProcessType())){
                sourceValue=StringUtils.trim_end_exclu(sourceValue, rule.getSubstrTarget());
            }else if(SpiderConstants.FIELD_PROCESS_TYPE_SUBSTRING_BEFORE.equals(rule.getProcessType())){
                sourceValue=StringUtils.trim_before_exclu(sourceValue, rule.getSubstrTarget());
            }else{}
        }
        return sourceValue;
    }
    private void xpath_put(Page page, Html pageHtml,SpiderField field) {
        if (StringUtils.isNotEmpty(field.getExtractAttr())) {
            String value=pageHtml.xpath(field.getExtractAttr()).get();
            value= processValue(value,field);//处理替换
            page.putField(field.getField(), value);
        }
    }
    private void css_put(Page page,Document pageDoc,SpiderField field) {
        String[] indexArr=field.getExtractIndex().split(",");
        String resStr="";
        if(!"1".equals(field.getExtractAttrFlag())){
            //非根据属性名取值
            for(String ix:indexArr){
                if(StringUtils.isNotEmpty(ix)){
                    String tempRes="";
                    try{
                        tempRes=  pageDoc.select(field.getExtractBy()).get(Integer.valueOf(ix)).html();
                    }catch (Exception ex){}
                    if(StringUtils.isNotEmpty(tempRes)){
                        resStr +=tempRes;
                        resStr +=",";
                    }
                }
            }
        }else{
            //根据属性名取值
            for(String ix:indexArr) {
                if (StringUtils.isNotEmpty(ix)) {
                    String tempRes="";
                    try {
                        tempRes= getAttributeByElement(pageDoc.select(field.getExtractBy()).get(Integer.valueOf(ix)),field.getExtractAttr());
                    }catch (Exception ex){}
                    if(StringUtils.isNotEmpty(tempRes)){
                        resStr +=tempRes;
                        resStr +=",";
                    }
                }
            }

        }
        if(resStr.endsWith(",")){
            resStr=resStr.substring(0,resStr.length()-1);
        }
        //处理替换
        resStr= processValue(resStr,field);
        page.putField(field.getField(),resStr);
    }
    private void constant_put(Page page,SpiderField field) {
        page.putField(field.getField(),field.getConstantValue());
    }

    private String getAttributeByElement(Element e, String attrName){
        // 判断如果属性名是href或者src
        String res = "";
        if ("href".equals(attrName) || "src".equals(attrName)) {
            // 因为要获取他们绝对路径
            res = e.attr("abs:" + attrName);
        } else {
            //不是href或者src
            res = e.attr(attrName);
        }
        return res;
    }
}
