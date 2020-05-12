package com.ruoyi.common.core.domain;

import java.util.Map;

/**
 * @author wujiyue
 */
public interface ICallBack {

    /**
     * 成功的回调函数
     */
    void onSuccess();
    /**
     * 失败的回调函数
     */
    void onFail();

    /**
     * 设置参数
     * @param map
     * @return
     */
    Map setParams(Map map);
}
