package com.zebra.api.consumer.service.impl;

import org.springframework.stereotype.Service;

import com.zebra.api.consumer.service.APIProviderService;
import com.zebra.api.consumer.service.base.BaseService;

@Service
public class APIProviderServiceImpl extends BaseService implements APIProviderService {

	@Override
	public String getAPISecurity(String key) {
		return super.returnHix();
	}

	@Override
	public String getNotice(Long noticeId) {
		return super.returnHix();
	}

	@Override
	public String getNoticeList() {
		return super.returnHix();
	}

}
