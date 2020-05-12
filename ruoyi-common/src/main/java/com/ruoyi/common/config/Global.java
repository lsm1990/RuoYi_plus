package com.ruoyi.common.config;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类
 * 
 * @author ruoyi
 */
@Configuration
public class Global
{
    private static String  name;

    private static String  version;

    private static String  copyrightYear;

    private static String  demoEnabled;

    private static Boolean addressEnabled;

    private static String  profile;

    public static Long ROLE_ID_DEFAULT=2L;//新增的用户赋予的默认角色的ID

    @Value("${ruoyi.name}")
    public void setName(String name)
    {
        Global.name = name;
    }

    @Value("${ruoyi.version}")
    public void setVersion(String version)
    {
        Global.version = version;
    }

    @Value("${ruoyi.copyrightYear}")
    public void setCopyrightYear(String copyrightYear)
    {
        Global.copyrightYear = copyrightYear;
    }

    @Value("${ruoyi.demoEnabled}")
    public void setDemoEnabled(String demoEnabled)
    {
        Global.demoEnabled = demoEnabled;
    }

    @Value("${ruoyi.addressEnabled}")
    public void setAddressEnabled(Boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    @Value("${ruoyi.profile}")
    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    /**
     * 获取项目名称
     */
    public static String getName()
    {
        return StringUtils.nvl(name, "RuoYi");
    }

    /**
     * 获取项目版本
     */
    public static String getVersion()
    {
        return StringUtils.nvl(version, "4.0.0");
    }

    /**
     * 获取版权年份
     */
    public static String getCopyrightYear()
    {
        return StringUtils.nvl(copyrightYear, "2019");
    }

    /**
     * 实例演示开关
     */
    public static String isDemoEnabled()
    {
        return StringUtils.nvl(demoEnabled, "true");
    }

    /**
     * 获取ip地址开关
     */
    public static Boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile()
    {
        return profile;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }

    /**
     * 获取素材上传路径
     */
    public static String getMaterialPath()
    {
        return getProfile() + "/material";
    }
    /**
     * 获取附件上传路径
     */
    public static String getAttachPath()
    {
        return getProfile() + "/attach";
    }
    /**
     * 获取资源上传路径
     */
    public static String getResourcePath()
    {
        return getProfile() + "/resource";
    }
    /**
     * 获取数据库备份路径
     */
    public static String getDbBackupPath()
    {
        return getProfile() + "/dbbackup";
    }
}
