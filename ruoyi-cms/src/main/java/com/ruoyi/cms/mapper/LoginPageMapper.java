package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.LoginPage;
import java.util.List;

/**
 * 登录页面Mapper接口
 * 
 * @author wujiyue
 * @date 2019-12-24
 */
public interface LoginPageMapper 
{
    /**
     * 查询登录页面
     * 
     * @param id 登录页面ID
     * @return 登录页面
     */
    public LoginPage selectLoginPageById(Long id);

    /**
     * 查询登录页面列表
     * 
     * @param loginPage 登录页面
     * @return 登录页面集合
     */
    public List<LoginPage> selectLoginPageList(LoginPage loginPage);

    /**
     * 新增登录页面
     * 
     * @param loginPage 登录页面
     * @return 结果
     */
    public int insertLoginPage(LoginPage loginPage);

    /**
     * 修改登录页面
     * 
     * @param loginPage 登录页面
     * @return 结果
     */
    public int updateLoginPage(LoginPage loginPage);

    /**
     * 删除登录页面
     * 
     * @param id 登录页面ID
     * @return 结果
     */
    public int deleteLoginPageById(Long id);

    /**
     * 批量删除登录页面
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLoginPageByIds(String[] ids);
}
