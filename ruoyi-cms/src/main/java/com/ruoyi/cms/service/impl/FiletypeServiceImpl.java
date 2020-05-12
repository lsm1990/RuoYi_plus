package com.ruoyi.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.FiletypeMapper;
import com.ruoyi.cms.domain.Filetype;
import com.ruoyi.cms.service.IFiletypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件类型Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-01
 */
@Service
public class FiletypeServiceImpl implements IFiletypeService 
{
    @Autowired
    private FiletypeMapper filetypeMapper;

    /**
     * 查询文件类型
     * 
     * @param id 文件类型ID
     * @return 文件类型
     */
    @Override
    public Filetype selectFiletypeById(Long id)
    {
        return filetypeMapper.selectFiletypeById(id);
    }

    @Override
    public Filetype selectFiletypeBySuffix(String suffix) {
        return filetypeMapper.selectFiletypeBySuffix(suffix);
    }

    /**
     * 查询文件类型列表
     * 
     * @param filetype 文件类型
     * @return 文件类型
     */
    @Override
    public List<Filetype> selectFiletypeList(Filetype filetype)
    {
        return filetypeMapper.selectFiletypeList(filetype);
    }

    /**
     * 新增文件类型
     * 
     * @param filetype 文件类型
     * @return 结果
     */
    @Override
    public int insertFiletype(Filetype filetype)
    {
        return filetypeMapper.insertFiletype(filetype);
    }

    /**
     * 修改文件类型
     * 
     * @param filetype 文件类型
     * @return 结果
     */
    @Override
    public int updateFiletype(Filetype filetype)
    {
        return filetypeMapper.updateFiletype(filetype);
    }

    /**
     * 删除文件类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFiletypeByIds(String ids)
    {
        return filetypeMapper.deleteFiletypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件类型信息
     * 
     * @param id 文件类型ID
     * @return 结果
     */
    @Override
    public int deleteFiletypeById(Long id)
    {
        return filetypeMapper.deleteFiletypeById(id);
    }

    @Override
    public List<Map<String, Object>> selectDictData() {
        return filetypeMapper.selectDictData();
    }
}
