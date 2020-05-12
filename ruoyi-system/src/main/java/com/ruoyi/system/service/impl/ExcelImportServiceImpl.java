package com.ruoyi.system.service.impl;


import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ImportExcelResult;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IExcelImportService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelImportServiceImpl implements IExcelImportService {

    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysUserService userService;

    @Override
    @Transactional
    public ImportExcelResult saveData(List<Object> list, String importType, SysUser currentUser) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        ImportExcelResult importExcelResult = new ImportExcelResult(0,result);
        for(int i=0;i<list.size();i++){
            switch (importType){
                case "1":
                    result.add(saveData((SysUser)list.get(i),i+1,importExcelResult,currentUser));
                    break;
                case "2":
                    result.add(saveData((SysDept)list.get(i),i+1,importExcelResult));
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return importExcelResult;
    }

    /**
     * 保存用户
     *
     * @param user
     * @param rowNum
     * @return
     */
    public Map<String, String> saveData(SysUser user, int rowNum, ImportExcelResult importExcelResult, SysUser currentUser) {
        StringBuilder msg = new StringBuilder("");
        //1.校验数据的合法性
        if(StringUtils.isEmpty(user.getLoginName())){
            msg.append("登录名称不能为空！");
        }
        if(StringUtils.isEmpty(user.getUserName())){
            msg.append("用户名称不能为空！");
        }
        if(StringUtils.isEmpty(user.getDept().getDeptName())){
            msg.append("部门名称不能为空！");
        }
        if(UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))){
            msg.append("登录名称系统内已经存在！");
        }
        SysDept dept = getDeptByName(user.getDeptName());
        if(dept!=null){
            if(!dept.getDelFlag().equals("0")){
                msg.append("部门已被删除！");
            }
            if(!dept.getStatus().equals("0")){
                msg.append("部门已被停用！");
            }
        }else{
            msg.append("部门不存在！");
        }
        if(!StringUtils.isEmpty(msg.toString())){
            return importExcelResultMap(user.getLoginName(),1,msg.toString(),rowNum);
        }
        //2.初始化默认值
        user.setDeptId(dept.getDeptId());
        if(StringUtils.isEmpty(user.getSex())){
            user.setSex("2");
        }else if("男".equals(user.getSex())){
            user.setSex("0");
        }else if("女".equals(user.getSex())){
            user.setSex("1");
        }
        String str=RandomUtils.nextInt()+"000000";
        user.setSalt(str.substring(0,6));
        user.setPassword(new Md5Hash(user.getUserName() + user.getPassword() + user.getSalt()).toHex().toString());
        user.setCreateBy(currentUser.getLoginName());
        user.setRoleIds(new Long[]{Global.ROLE_ID_DEFAULT});
        user.setPostIds(new Long[]{});
        //3.保存
        userService.insertUser(user);
        importExcelResult.setSuccessNum(importExcelResult.getSuccessNum()+1);
        return importExcelResultMap(user.getLoginName(),0,"添加成功！",rowNum);
    }

    /**
     * 保存部门
     *
     * @param dept
     * @param rowNum
     * @return
     */
    public Map<String, String> saveData(SysDept dept, int rowNum, ImportExcelResult importExcelResult) {
        StringBuilder msg = new StringBuilder("");
        //1.校验数据的合法性
        SysDept tempDept = null;
        if(StringUtils.isEmpty(dept.getDeptName())){
            msg.append("部门名称不能为空！");
        }else{
            tempDept = getDeptByName(dept.getDeptName());
            if(tempDept!=null){
                msg.append("部门名称（"+dept.getDeptName()+"）已经存在！");
            }
        }
        if(StringUtils.isEmpty(dept.getParentName())){
            msg.append("上级部门名称不能为空！");
        }
        if(StringUtils.isEmpty(dept.getOrderNum())){
            msg.append("显示顺序不能为空！");
        }else if(!dept.getOrderNum().matches("\\d{1,4}")){
            msg.append("显示顺序必须是不超过4位的整数！");
        }
        tempDept = getDeptByName(dept.getParentName());
        if(tempDept==null){
            msg.append("上级部门（").append(dept.getParentName()).append("）不存在！");
        }else{

        }
        if(!StringUtils.isEmpty(msg.toString())){
            return importExcelResultMap(dept.getDeptName(),1,msg.toString(),rowNum);
        }
        //2.初始化默认值
        dept.setParentId(tempDept.getDeptId());
        //3.保存
        deptService.insertDept(dept);
        importExcelResult.setSuccessNum(importExcelResult.getSuccessNum()+1);
        return importExcelResultMap(dept.getDeptName(),0,"添加成功！",rowNum);
    }

    private SysDept getDeptByName(String deptName){
        SysDept dept = new SysDept();
        //按照名称精确查询该名称的部门
        dept.getParams().put("equalDeptName",deptName);
        List<SysDept> depts = deptService.selectDeptList(dept);
        if(depts == null || depts.isEmpty()){
            return null;
        }
        return depts.get(0);
    }
    /**
     * 每行导入结果
     * @param name
     * @param code
     * @param msg
     * @return
     */
    private Map<String,String> importExcelResultMap(String name,Integer code,String msg,Integer rowNum){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("code",code.toString());
        map.put("msg",msg);
        map.put("rowNum",rowNum.toString());
        return map;
    }

}
