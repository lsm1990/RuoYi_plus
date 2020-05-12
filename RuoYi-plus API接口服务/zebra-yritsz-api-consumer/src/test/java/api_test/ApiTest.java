package api_test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.MimeTypeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zebra.api.consumer.util.DateUtil;
import com.zebra.api.consumer.util.HttpUtil;
import com.zebra.api.consumer.util.SignConstants;
import com.zebra.api.consumer.util.SignConstants.SignType;
import com.zebra.api.consumer.util.SignUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiTest {
	private final static String KEY_HEAND = "hand_temple_key";
	private final static String KEY = "key_2019xertybd66!2";
	private final static String SECRET = "2134567897686";

	public static void main(String[] args) throws Exception {
		HttpUtil httpUtil = HttpUtil.getInstance("utf-8", 10000, 10000);
		Map<String, Object> map = new HashMap<>();
		Map<String, String> headMap = new HashMap<>();
		String tokenTime = DateUtil.format(new Date(), SignConstants.TOKEN_TIME_FORMT);
		String tokenNonceStr = UUID.randomUUID().toString();
		map.put("tokenTime", tokenTime);
		map.put("tokenNonceStr", tokenNonceStr);
		String sin = SignUtil.generateSignature(map, SECRET, SignType.HMACSHA256);
		JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
		jsonObject.put("sign", sin);
		log.info("[信息]请求-" + jsonObject.toString());
		headMap.put(KEY_HEAND, KEY);
		String result = httpUtil.sendHttpPost("http://127.0.0.1:1213/getNotice/1", null, headMap,
				jsonObject.toString(), MimeTypeUtils.APPLICATION_JSON);
		log.info("[信息]响应-" + result);
	}

}
