package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.Attachment;
import com.ruoyi.cms.domain.Pv;
import com.ruoyi.cms.service.CmsService;
import com.ruoyi.cms.service.IAttachmentService;
import com.ruoyi.cms.service.IPvService;
import com.ruoyi.cms.service.PVQueueService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cms模块的通用操作
 */
@Controller
public class CmsController extends BaseController {

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IPvService pvService;
    @Autowired
    CmsService cms;
    /**
     * 上传附件请求
     */
    @PostMapping("/cms/uploadAttach")
    @ResponseBody
    public AjaxResult uploadAttach(@RequestParam("zid") String zid,String sort, MultipartFile file) throws Exception
    {
        try
        {
            if(StringUtils.isEmpty(zid)){
                return AjaxResult.error("参数[zid]不能为空!");
            }
            Attachment attachment=new Attachment();
            if(StringUtils.isEmpty(sort)){
                sort="1";
            }
            int sortNum=1;
            try{
                sortNum=Integer.valueOf(sort);
            }catch (Exception ex){}
            attachment.setSort(sortNum);
            String suffix=FileUploadUtils.getExtension(file);
            String fileType= MimeTypeUtils.MATERIAL_TYPE_OTHER;//文件类型-其它
            if(FileUploadUtils.isImage(suffix)){
                fileType=MimeTypeUtils.MATERIAL_TYPE_IMG;
            }else if(FileUploadUtils.isText(suffix)){
                fileType=MimeTypeUtils.MATERIAL_TYPE_TEXT;
            }else if(FileUploadUtils.isAudio(suffix)){
                fileType=MimeTypeUtils.MATERIAL_TYPE_AUDIO;
            }else if(FileUploadUtils.isVideo(suffix)){
                fileType=MimeTypeUtils.MATERIAL_TYPE_VIDEO;
            }else if(FileUploadUtils.isZip(suffix)){
                fileType=MimeTypeUtils.MATERIAL_TYPE_ZIP;
            }else{
                fileType= MimeTypeUtils.MATERIAL_TYPE_OTHER;
            }
            // 上传并返回新文件名称
            String path = FileUploadUtils.upload(Global.getAttachPath(), file);
            String url = serverConfig.getUrl() + path;
            attachment.setFileType(fileType);
            attachment.setFileUrl(url);
            attachment.setFilePath(path);
            attachment.setSize(file.getSize());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setZid(zid);

            attachmentService.insertAttachment(attachment);
            return AjaxResult.success(attachment);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 根据组ID查询附件列表
     */
    @PostMapping("/cms/selectAttachmentsByZid")
    @ResponseBody
    public Object selectAttachmentsByZid(@RequestParam("zid")String  zid)
    {
        List<Attachment> list = attachmentService.selectAttachmentsByZid(zid);
        return AjaxResult.success(list);
    }

    /**
     * 删除附件
     */
    @Log(title = "附件", businessType = BusinessType.DELETE)
    @PostMapping( "/cms/deleteAttachments")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        SysUser user= ShiroUtils.getSysUser();
        boolean checkOkFlag=true;
        if(!user.isAdmin()){
            //非管理员用户调用该接口删除只能删除自己的附件
            String[] arr= Convert.toStrArray(ids);
            for(String id:arr){
                if(StringUtils.isNotEmpty(id)){
                    Attachment attachment=attachmentService.selectAttachmentById(id);
                    if(attachment!=null){
                        if(!attachment.getUserId().equals(user.getUserId().toString())){
                            checkOkFlag=false;
                            break;
                        }
                    }
                }
            }
        }
        if(!checkOkFlag){
            return AjaxResult.error("您只能删除自己上传的附件!");
        }
        return toAjax(attachmentService.deleteAttachmentByIds(ids));
    }


    /**
     * 上传素材
     */
    @PostMapping("/cms/uploadMaterial")
    @ResponseBody
    public AjaxResult uploadMaterial(MultipartFile file) throws Exception
    {
        try
        {
            // 上传并返回新文件名称
            String path = FileUploadUtils.upload(Global.getMaterialPath(), file);
            String url = serverConfig.getUrl() + path;
            Map<String,Object> data=new HashMap();
            int width=FileUploadUtils.getImgWidth(file);
            int height=FileUploadUtils.getImgHeight(file);
            data.put("path",path);
            data.put("url",url);
            data.put("width",width);
            data.put("suffix",FileUploadUtils.getExtension(file));
            data.put("height",height);
            data.put("size",file.getSize());
            data.put("name",file.getOriginalFilename());
            return AjaxResult.success(data);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    @Autowired
    PVQueueService pvQueueService;

    @PostMapping( "/page/view")
    @ResponseBody
    public AjaxResult pv(Pv pv, HttpServletRequest request)
    {
        pvQueueService.pushPvQueue(request,pv);
        return success();
    }

    @PostMapping( "/cms/clearSiteInfoCache")
    @ResponseBody
    public AjaxResult clearSiteInfoCache(Pv pv, HttpServletRequest request)
    {
        cms.clearCache();
        return success();
    }
}
