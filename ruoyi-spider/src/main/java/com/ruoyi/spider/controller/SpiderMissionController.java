package com.ruoyi.spider.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.service.ISpiderConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.spider.domain.SpiderMission;
import com.ruoyi.spider.service.ISpiderMissionService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 爬虫任务Controller
 * 
 * @author wujiyue
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/spider/spiderMission")
public class SpiderMissionController extends BaseController
{
    private String prefix = "spider/spiderMission";

    @Autowired
    private ISpiderMissionService spiderMissionService;

    @Autowired
    private ISpiderConfigService spiderConfigService;

    @RequiresPermissions("spider:spiderMission:view")
    @GetMapping()
    public String spiderMission()
    {
        return prefix + "/spiderMission";
    }

    /**
     * 查询爬虫任务列表
     */
    @RequiresPermissions("spider:spiderMission:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SpiderMission spiderMission)
    {
        startPage();
        List<SpiderMission> list = spiderMissionService.selectSpiderMissionList(spiderMission);
        return getDataTable(list);
    }

    /**
     * 导出爬虫任务列表
     */
    @RequiresPermissions("spider:spiderMission:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderMission spiderMission)
    {
        List<SpiderMission> list = spiderMissionService.selectSpiderMissionList(spiderMission);
        ExcelUtil<SpiderMission> util = new ExcelUtil<SpiderMission>(SpiderMission.class);
        return util.exportExcel(list, "spiderMission");
    }

    /**
     * 新增爬虫任务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存爬虫任务
     */
    @RequiresPermissions("spider:spiderMission:add")
    @Log(title = "爬虫任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderMission spiderMission)
    {
        String entryUrls=spiderMission.getEntryUrls();
        String[] arr=null;
        if(entryUrls.contains(",")){
            //前台多条传过来用逗号分隔
            arr=entryUrls.split(",");

        }else{
            //前台多条传过来用换行分隔
            arr=entryUrls.split("\r\n");
        }
        if(arr==null||arr.length==0){
            return AjaxResult.error("入口URL必填!多条用逗号分隔或者换行分隔!");
        }
        boolean isUrlFlag=true;
        String tempUrl="";
        String urlAll="";
        for(String url:arr){
            if(!isURL(url)){
                isUrlFlag=false;
                tempUrl=url;
                break;
            }else{
                urlAll+=url+",";
            }
        }
        if(!isUrlFlag){
            return AjaxResult.error("["+tempUrl+"]不是一个有效的url!");
        }
        if(isUrlFlag){
            if(urlAll.endsWith(",")){
                urlAll=urlAll.substring(0,urlAll.length()-1);
                spiderMission.setEntryUrls(urlAll);
            }
        }
        return toAjax(spiderMissionService.insertSpiderMission(spiderMission));
    }

    private static boolean isURL(String str){
        str = str.toLowerCase();
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        return str.matches(regex);
     }

    /**
     * 修改爬虫任务
     */
    @GetMapping("/edit/{missionId}")
    public String edit(@PathVariable("missionId") Long missionId, ModelMap mmap)
    {
        SpiderMission spiderMission = spiderMissionService.selectSpiderMissionById(missionId);
        SpiderConfig config = spiderConfigService.selectSpiderConfigById(spiderMission.getSpiderConfigId());
        if(config!=null){
            mmap.put("spiderConfigName", config.getSpiderName());
        }
        mmap.put("spiderMission", spiderMission);
        return prefix + "/edit";
    }

    /**
     * 修改保存爬虫任务
     */
    @RequiresPermissions("spider:spiderMission:edit")
    @Log(title = "爬虫任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderMission spiderMission)
    {
        String entryUrls=spiderMission.getEntryUrls();
        String[] arr=null;
        if(entryUrls.contains(",")){
            //前台多条传过来用逗号分隔
            arr=entryUrls.split(",");

        }else{
            //前台多条传过来用换行分隔
            arr=entryUrls.split("\r\n");
        }
        if(arr==null||arr.length==0){
            return AjaxResult.error("入口URL必填!多条用逗号分隔或者换行分隔!");
        }
        boolean isUrlFlag=true;
        String tempUrl="";
        String urlAll="";
        for(String url:arr){
            if(!isURL(url)){
                isUrlFlag=false;
                tempUrl=url;
                break;
            }else{
                urlAll+=url+",";
            }
        }
        if(!isUrlFlag){
            return AjaxResult.error("["+tempUrl+"]不是一个有效的url!");
        }
        if(isUrlFlag){
            if(urlAll.endsWith(",")){
                urlAll=urlAll.substring(0,urlAll.length()-1);
                spiderMission.setEntryUrls(urlAll);
            }
        }
        return toAjax(spiderMissionService.updateSpiderMission(spiderMission));
    }

    /**
     * 删除爬虫任务
     */
    @RequiresPermissions("spider:spiderMission:remove")
    @Log(title = "爬虫任务", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(spiderMissionService.deleteSpiderMissionByIds(ids));
    }

    /**
     * 启动爬虫任务
     */
    @PostMapping( "/runSpiderMission/{missionId}")
    @ResponseBody
    public AjaxResult runSpiderMission(@PathVariable("missionId") String missionId)
    {
        return spiderMissionService.runSpiderMission(missionId);

    }

    /**
     * 打开任务选择爬虫界面
     * @return
     */
    @GetMapping("/openSelectSpiderConfig")
    public String openSelectSpiderConfig()
    {
        return prefix + "/selectSpiderConfig";
    }


}
