package com.zebra.api.consumer.service.base;

import com.alibaba.fastjson.JSONObject;
import com.zebra.api.consumer.bean.Json;

public class BaseService {
	/**
	 * 熔断响应
	 *
	 * @return
	 */
	protected String returnHix() {
		return JSONObject.toJSONString(Json.errorHix());
	}
}
