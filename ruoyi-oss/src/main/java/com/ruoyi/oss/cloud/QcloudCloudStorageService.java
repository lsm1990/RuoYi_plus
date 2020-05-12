package com.ruoyi.oss.cloud;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.oss.domain.OssException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 腾讯云存储
 */
public class QcloudCloudStorageService extends CloudStorageService
{
    private COSClient client;

    public QcloudCloudStorageService(CloudStorageConfig config)
    {
        this.config = config;
        // 初始化
        init();
    }

    private void init()
    {
        COSCredentials credentials = new BasicCOSCredentials(config.getQcloudSecretId(),
                config.getQcloudSecretKey());
        // 设置bucket所在的区域，最新sdk不再支持简写，请填写完整
        Region region = new Region(config.getQcloudRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        client = new COSClient(credentials, clientConfig);
    }

    @Override
    public String upload(byte[] data, String path)
    {
        if (!path.startsWith("/"))
        {
            path = "/" + path;
        }
        File file = null;
        // 创建一个临时目录文件，结束后删除,腾讯云这个操作跟SB没区别，用流上传麻烦更多，权衡以后先这么做
        try
        {
            file = FileUtils.createTempFile(path, data);
            PutObjectRequest putObjectRequest = new PutObjectRequest(config.getQcloudBucketName(), path, file);
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        }
        catch (Exception e)
        {
            throw new OssException("文件上传失败，" + e.getMessage());
        }
        finally
        {
            if (null != file) file.delete();
        }
        return config.getQcloudDomain() + path;
    }

    @Override
    public String upload(InputStream inputStream, String path)
    {
        try
        {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        }
        catch (IOException e)
        {
            throw new OssException("上传文件失败");
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix)
    {
        return upload(data, getPath(config.getQcloudPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix)
    {
        return upload(inputStream, getPath(config.getQcloudPrefix(), suffix));
    }
}
