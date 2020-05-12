package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Filetype;
import java.util.List;
import java.util.Map;

/**
 * 文件类型Service接口
 * 
 * @author wujiyue
 * @date 2019-11-01
 */
public interface IFiletypeService 
{
    /**
     * 查询文件类型
     * 
     * @param id 文件类型ID
     * @return 文件类型
     */
    public Filetype selectFiletypeById(Long id);

    /**
     * 查询文件类型
     *
     * @param suffix 后缀
     * @return 文件类型
     */
    public Filetype selectFiletypeBySuffix(String suffix);
    /**
     * 查询文件类型列表
     * 
     * @param filetype 文件类型
     * @return 文件类型集合
     */
    public List<Filetype> selectFiletypeList(Filetype filetype);

    /**
     * 新增文件类型
     * 
     * @param filetype 文件类型
     * @return 结果
     */
    public int insertFiletype(Filetype filetype);

    /**
     * 修改文件类型
     * 
     * @param filetype 文件类型
     * @return 结果
     */
    public int updateFiletype(Filetype filetype);

    /**
     * 批量删除文件类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFiletypeByIds(String ids);

    /**
     * 删除文件类型信息
     * 
     * @param id 文件类型ID
     * @return 结果
     */
    public int deleteFiletypeById(Long id);

    /**
     * 查询字典格式的数据
     * dictValue,dictLabel
     * @return
     */
    public List<Map<String,Object>> selectDictData();
}
