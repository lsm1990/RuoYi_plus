package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Pv;
import java.util.List;

/**
 * PVService接口
 * 
 * @author wujiyue
 * @date 2019-11-29
 */
public interface IPvService 
{
    /**
     * 查询PV
     * 
     * @param id PVID
     * @return PV
     */
    public Pv selectPvById(Long id);

    /**
     * 查询PV列表
     * 
     * @param pv PV
     * @return PV集合
     */
    public List<Pv> selectPvList(Pv pv);

    /**
     * 新增PV
     * 
     * @param pv PV
     * @return 结果
     */
    public int insertPv(Pv pv);
    /**
     * 批量新增PV
     *
     * @param pvs PV
     * @return 结果
     */
    public int insertPvBatch(List<Pv> pvs);
    /**
     * 修改PV
     * 
     * @param pv PV
     * @return 结果
     */
    public int updatePv(Pv pv);

    /**
     * 批量删除PV
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePvByIds(String ids);

    /**
     * 删除PV信息
     * 
     * @param id PVID
     * @return 结果
     */
    public int deletePvById(Long id);
}
