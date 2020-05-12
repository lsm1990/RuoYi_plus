package com.ruoyi.cms.util;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageUtil {
    static Logger logger = LoggerFactory.getLogger(DownloadImageUtil.class);
    static Cache<String, String> downloadCache = CacheUtil.newLRUCache(1000);

    public static void main(String[] args) {
        System.out.println(downloadImageToLocal("http://old.duoguyu.com/uploads/201809/09/180909053041227.jpg","/test/test2"));
    }
    /**
     * 检测一个网络路径文件是否存在
     * @param url
     * @return
     */
    public static boolean checkNetUrlExists(String url){
        try{
            URL u = new URL(url);
            HttpURLConnection urlcon = (HttpURLConnection) u.openConnection();
            if(urlcon.getResponseCode()==200){
                return true;
            }
        }catch (Exception ex){
        }
        return false;
    }

    /**
     * 本地化网络路径图片
     * @param urlPath 网络图片
     * @param appendPath 追加一层目录如/book或/article
     * @return 返回保存到本地的相对路径
     */
    public static String downloadImageToLocal(String urlPath,String appendPath){
        try{
            if(!checkNetUrlExists(urlPath)){
                return "";
            }
            String saveBathPath=Global.getUploadPath();
            if(StringUtils.isNotEmpty(appendPath)){
                appendPath=appendPath.startsWith("/")?appendPath:"/"+appendPath;
                saveBathPath+=appendPath;
            }
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //这里通过httpclient下载之前抓取到的图片网址，并放在对应的文件中
            //String urlPath="http://v3cdn.duoguyu.com/oss/uploads/201803/08/180308100445561.jpg";
            HttpGet httpget = new HttpGet(urlPath);
            String extension=com.google.common.io.Files.getFileExtension(urlPath);
            String name=com.google.common.io.Files.getNameWithoutExtension(urlPath);
            String fileName = DateUtils.datePath() + "/" + FileUploadUtils.encodingFilename(name) + "." + extension;
            File file = FileUploadUtils.getAbsoluteFile(saveBathPath,fileName);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            try {
                FileOutputStream fout = new FileOutputStream(file);
                int l = -1;
                byte[] tmp = new byte[1024];
                while ((l = in.read(tmp)) != -1) {
                    fout.write(tmp,0,l);
                }
                fout.flush();
                fout.close();
            } finally {
                in.close();
            }
            return FileUploadUtils.getPathFileName(saveBathPath,fileName);
        }catch (Exception ex){
            ex.printStackTrace();
            return "error";
        }
    }

}
