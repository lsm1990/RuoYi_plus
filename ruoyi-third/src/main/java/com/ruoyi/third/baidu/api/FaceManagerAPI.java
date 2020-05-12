package com.ruoyi.third.baidu.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.third.baidu.bean.BaiduApiResult;
import com.ruoyi.third.baidu.util.Base64Util;
import com.ruoyi.third.baidu.util.FileUtil;
import com.ruoyi.third.baidu.util.HttpUtil;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人脸库管理
 * @author wujiyue
 */
public class FaceManagerAPI extends BaiduAi {

    public final static String GROUP_DEFAULT="default";
    private final static String URL_USER_FACE_ADD = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
    private final static String URL_USER_GET = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";
    private final static String URL_USER_GETFACELIST = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/getlist";
    private final static String URL_GROUP_USERS = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getusers";
    private final static String URL_USER_UPDATE = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
    private final static String URL_USER_FACE_DEL ="https://aip.baidubce.com/rest/2.0/face/v3/faceset/face/delete";
    private final static String URL_USER_DEL ="https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";
    private final static String URL_USER_COPY = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/copy";
    private final static String URL_GROUP_LIST = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/getlist";
    private final static String URL_GROUP_ADD = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add";
    private final static String URL_GROUP_DEL = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete";
    private static String getImgParam(String... localPaths){
        String res="";
       if(localPaths!=null && localPaths.length>0){
           for(int i=0;i<localPaths.length;i++){
               String path=localPaths[i];
               if(new File(path).exists()){
                   byte[] imgData = new byte[0];
                   try {
                       imgData = FileUtil.readFileByBytes(path);
                       String imgStr = Base64Util.encode(imgData);
                       res +=imgStr+",";
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
       }
        if(res.endsWith(",")){
            res=res.substring(0,res.length()-1);
        }
        return res;
    }

    /**
     * 注册人脸
     * @param uid
     * @param userInfo
     * @param localPath
     * @return
     */
    public static BaiduApiResult add(String uid, String userInfo, String  localPath) {
        try {
            String imgParam=getImgParam(localPath);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgParam);
            map.put("group_id", GROUP_DEFAULT);
            map.put("user_id", uid);
            map.put("user_info", userInfo);
            map.put("liveness_control", "NONE");//NORMAL
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_FACE_ADD, getAccessToken(), param);
            // System.out.println(result);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于对人脸库中指定用户，更新其下的人脸图像
     * @param uid
     * @param userInfo
     * @param localPaths
     * @return
     */
    public static BaiduApiResult update(String uid,String userInfo,String... localPaths) {
        try {
            String imgParam=getImgParam(localPaths);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgParam);
            map.put("group_id",GROUP_DEFAULT);
            map.put("user_id", uid);
            map.put("user_info",userInfo);
            map.put("liveness_control", "NONE");//NORMAL
            //FACE_TOKEN BASE64 URL
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_UPDATE, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除用户的某一张人脸，如果该用户只有一张人脸图片，则同时删除用户。
     * @param uid
     * @param face_token
     * @return
     */
    public static BaiduApiResult deleteFace(String uid,String face_token) {
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("face_token", face_token);
            map.put("group_id",GROUP_DEFAULT);
            map.put("user_id", uid);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_FACE_DEL, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于将用户从某个组中删除
     * @param uid
     * @return
     */
    public static BaiduApiResult deleteUser(String uid) {
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("group_id",GROUP_DEFAULT);
            map.put("user_id", uid);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_DEL, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于将已经存在于人脸库中的用户复制到一个新的组
     * @param uid
     * @param srcGroupId
     * @param newGroupId
     * @return
     */
    public static BaiduApiResult copyUser(String uid,String srcGroupId,String newGroupId) {
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("src_group_id",GROUP_DEFAULT);
            map.put("dst_group_id",GROUP_DEFAULT);
            map.put("user_id", uid);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_COPY, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取人脸库中某个用户的信息(user_info信息和用户所属的组)
     * @param uid
     * @return
     */
    public static BaiduApiResult getUser(String uid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user_id", uid);
            map.put("group_id", GROUP_DEFAULT);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_GET, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用于获取一个用户的全部人脸列表
     * @param uid
     * @return
     */
    public static BaiduApiResult getUserFaceList(String uid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user_id", uid);
            map.put("group_id",GROUP_DEFAULT);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_USER_GETFACELIST, getAccessToken(), param);
            System.out.println(result);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用于查询指定用户组中的用户列表
     * @return
     */
    public static BaiduApiResult getUsers() {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("group_id", GROUP_DEFAULT);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_GROUP_USERS, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /********************************用户组管理*************************************/


    /**
     * 组列表查询
     * @return
     */
    public static BaiduApiResult getGroupList() {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("start", 0);
            map.put("length", 100);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_GROUP_LIST, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static BaiduApiResult addGroup(String name) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("group_id", name);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_GROUP_ADD, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BaiduApiResult delGroup(String name) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("group_id", name);
            String param = JSON.toJSONString(map);
            String result = HttpUtil.post(URL_GROUP_DEL, getAccessToken(), param);
            return BaiduApiResult.create(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
       String[] arr={"C:\\Users\\Administrator\\Desktop\\22.jpg","C:\\Users\\Administrator\\Desktop\\11.jpg"};

      //  bean.BaiduApiResult baiduApiResult= add("testuser","aaa123","C:\\Users\\Administrator\\Desktop\\11.jpg");

       // bean.BaiduApiResult baiduApiResult= update("testuser", "111aaa",arr);
       // bean.BaiduApiResult baiduApiResult= delete("testuser","fbcd1aae22ac2a5e1abb75d7fb3d29eb");
        /*bean.BaiduApiResult baiduApiResult=getUser("testuser");*/
    //  bean.BaiduApiResult baiduApiResult= getUserFaceList("testuser");

     //bean.BaiduApiResult baiduApiResult= getUsers();
        // bean.BaiduApiResult baiduApiResult=deleteUser("testuser");

       // bean.BaiduApiResult baiduApiResult= copyUser("testuser","default","newg");
    BaiduApiResult baiduApiResult= getGroupList();
        // bean.BaiduApiResult baiduApiResult=addGroup("ng");
        //    bean.BaiduApiResult baiduApiResult=delGroup("group_repeat");
        if(baiduApiResult.isSucceed()){
            System.out.println("成功:");
            JSONObject res=baiduApiResult.get("result");

            //JSONArray  array= JSONArray.parseArray(res.get("group_id_list").toString());
           List<String> list= JSONArray.parseArray(res.get("group_id_list").toString(),String.class);

            System.out.println(list.get(0));
        }else{
            String res= baiduApiResult.getErrorMsg()+"错误代码:"+baiduApiResult.getErrorCode();

            System.out.println(res);
        }
    }
}
