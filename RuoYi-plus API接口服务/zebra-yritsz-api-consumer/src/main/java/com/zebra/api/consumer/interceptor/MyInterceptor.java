/**
 *@author am
 */
package com.zebra.api.consumer.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zebra.api.consumer.bean.Json;
import com.zebra.api.consumer.enums.ResultEnum;
import com.zebra.api.consumer.service.APIProviderService;
import com.zebra.api.consumer.util.Constant;
import com.zebra.api.consumer.util.RequestUtil;
import com.zebra.api.consumer.util.SignConstants;
import com.zebra.api.consumer.util.SignConstants.SignType;
import com.zebra.api.consumer.util.SignUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {

	@Value("${api.security.enabled}")
	private Boolean API_SECURITY;

	private static final String RESULT_CODE = "code";
	private static final String RESULT_MSG = "msg";
	private static final String RESULT_OBJECT = "obj";
	private static final String RESULT_SECRET = "apiSecret";
	@Autowired
	private APIProviderService apiProviderService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean flag = true;
		request.setAttribute(Constant.TIME_START, System.currentTimeMillis());
		String key = "";
		String tokenKey = "";
		key = request.getHeader(Constant.HAND_KEY);
		if (!API_SECURITY) {
			log.info("[信息]-未开启api验签");
			return true;
		}
		if (StringUtils.isEmpty(key)) {
			log.info("[信息]-校验签名失败-【{}】-请求头信息不存在", request.getRequestURI());
			flag = false;
		} else {
			MDC.put("index", key);
			log.info("[地址]-" + request.getRequestURI());
			String apiResult = apiProviderService.getAPISecurity(key);
			JSONObject jsonObject = JSONObject.parseObject(apiResult);
			if (!ResultEnum.SUCCESS.getCode().equals(jsonObject.get(RESULT_CODE))) {
				log.info("[信息]-校验签名【提供模块】失败-失败原因：{}", jsonObject.get(RESULT_MSG));
				flag = false;
			} else {
				String secret = jsonObject.getJSONObject(RESULT_OBJECT).getString(RESULT_SECRET);
				if (secret == null) {
					log.info("[信息]-校验签名失败-请求头信息错误：{}", key);
					flag = false;
				} else {
					tokenKey = RequestUtil.readJSONString(request);
					log.info("[请求]-" + tokenKey);
					if (StringUtils.isEmpty(tokenKey)) {
						log.info("[信息]校验签名失败：签名信息不存在");
						flag = false;
					} else {
						try {
							if (!SignUtil.getSignIsNotKey(tokenKey)) {
								flag = false;
							} else {
								Map<String, Object> map = JSON.parseObject(tokenKey);
								log.debug("[信息]获取签名：{}", map.toString());
								String signParam = map.get(SignConstants.FIELD_SIGN).toString();
								String TOKEN_TIME = map.get(SignConstants.TOKEN_TIME).toString();
								String TOKEN_NONCE_STR = map.get(SignConstants.TOKEN_NONCE_STR).toString();
								if (!SignUtil.getSignIsNull(signParam, TOKEN_TIME, TOKEN_NONCE_STR)) {
									flag = false;
								} else {
									String singServer = SignUtil.generateSignature(map, secret, SignType.HMACSHA256);
									if (!signParam.equals(singServer)) {
										log.info("[信息]签名校验失败：{}", singServer);
										flag = false;
									}
								}
							}

						} catch (Exception e) {
							log.error("[信息]校验签名时发生异常", e);
							flag = false;
						}
					}
				}
			}
		}
		if (!flag) {
			PrintWriter out = null;
			try {
				Object res = JSONObject.toJSON(Json.other(ResultEnum.ERROR_TOKEN));
				out = response.getWriter();
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				out.println(res.toString());
				log.info("[信息]-响应：{}", res.toString());
				MDC.remove("index");
				MDC.clear();
				out.flush();
				out.close();
				return false;
			} catch (Exception e) {
				log.error("[信息]校验token失败：返回时发生异常", e);
				response.sendError(500);
				MDC.remove("index");
				MDC.clear();
				return false;
			}
		}
		request.setAttribute(Constant.REQUEST_PARAM, tokenKey);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Long attribute = (Long) request.getAttribute(Constant.TIME_START);
		long time = System.currentTimeMillis() - attribute;
		log.info("[信息]-响应时间" + time + "毫秒");
		MDC.remove("index");
		MDC.clear();
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
