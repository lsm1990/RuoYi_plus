package com.zebra.api.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zebra.api.consumer.service.impl.APIProviderServiceImpl;

@FeignClient(value = "${demo.server.name}", fallback = APIProviderServiceImpl.class)
public interface APIProviderService {
	/**
	 * 根据key获取api安全信息
	 *
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/getAPISecurity/{key}", method = RequestMethod.POST)
	public String getAPISecurity(@PathVariable(value = "key") String key);

	/**
	 * 获取公告信息
	 *
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/getNotice/{noticeId}", method = RequestMethod.GET)
	public String getNotice(@PathVariable(value = "noticeId") Long noticeId);

	/**
	 * 获取公告列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	public String getNoticeList();
}
