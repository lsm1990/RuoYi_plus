package com.ruoyi.third.baidu.bean;/*
 * FileName：ErrCode.java 
 * <p>
 * Copyright (c) 2017-2020, <a href="http://www.webcsn.com">hermit (794890569@qq.com)</a>.
 * <p>
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl-3.0.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 返回码说明
 * @author wujiyue
 */
public class BaiduErrCode {
	
	private static Map<String,String> codeMap = new HashMap<String,String>();
	
	static {
		codeMap.put("-1","系统繁忙");
		codeMap.put("0","请求成功");
		codeMap.put("4","集群超限额");
		codeMap.put("6","没有接口权限");
		codeMap.put("17","每天流量超限额");
		codeMap.put("18","QPS超限额");
		codeMap.put("19","请求总量超限额");
		codeMap.put("100","无效的access_token参数");
		codeMap.put("110","Access Token失效");
		codeMap.put("111","Access token过期");

		codeMap.put("222001","必要参数未传入");
		codeMap.put("222002","参数[start]格式错误");
		codeMap.put("222003","参数[length]格式错误");
		codeMap.put("222004","参数[op_app_id_list]格式错误");
		codeMap.put("222005","参数[group_id_list]格式错误");
		codeMap.put("222006","参数group_id格式错误");
		codeMap.put("222007","参数uid格式错误");
		codeMap.put("222008","参数face_id格式错误");
		codeMap.put("222009","参数quality_conf格式错误");
		codeMap.put("222010","参数user_info格式错误");
		codeMap.put("222011","参数[uid_list]格式错误");
		codeMap.put("222012","参数[op_app_id]格式错误");
		codeMap.put("222013","参数[image]格式错误");
		codeMap.put("222014","参数[app_id]格式错误");
		codeMap.put("222015","参数[image_type]格式错误");
		codeMap.put("222016","参数[max_face_num]格式错误");
		codeMap.put("222017","参数[face_field]格式错误");
		codeMap.put("222018","参数[user_id]格式错误");
		codeMap.put("222019","参数[quality_control]格式错误");
		codeMap.put("222020","参数[liveness_control]格式错误");
		codeMap.put("222021","参数[max_user_num]格式错误");
		codeMap.put("222022","参数[id_card_number]格式错误");
		codeMap.put("222023","参数[name]格式错误");
		codeMap.put("222024","参数[face_type]格式错误");
		codeMap.put("222025","参数[face_token]格式错误");
		codeMap.put("222026","参数[max_star_num]格式错误");

		codeMap.put("222201","服务端请求失败或网络不可用");
		codeMap.put("222202","图片中没有人脸");
		codeMap.put("222203","无法解析人脸");
		codeMap.put("222204","从图片的url下载图片失败");
		codeMap.put("222205","服务端请求失败或网络不可用");
		codeMap.put("222206","服务端请求失败rtse service return fail");
		codeMap.put("222207","未找到匹配的用户");
		codeMap.put("222208","图片的数量错误");
		codeMap.put("222209","face token不存在");
		codeMap.put("222300","人脸图片添加失败");
		codeMap.put("222301","获取人脸图片失败");
		codeMap.put("222302","system error");
		codeMap.put("222303","获取人脸图片失败");

		codeMap.put("223100","操作的用户组不存在");
		codeMap.put("223101","该用户组已存在");
		codeMap.put("223102","该用户已存在");
		codeMap.put("223103","找不到该用户");
		codeMap.put("223104","group_list包含组数量过多");
		codeMap.put("223105","该人脸已存在");
		codeMap.put("223106","该人脸不存在");

		codeMap.put("223110","uid_list包含数量过多");
		codeMap.put("223111","目标用户组不存在");
		codeMap.put("223112","quality_conf格式不正确");
		codeMap.put("223113","人脸有被遮挡");
		codeMap.put("223114","人脸模糊");
		codeMap.put("223115","人脸光照不好");
		codeMap.put("223116","人脸不完整");
		codeMap.put("223117","app_list包含app数量过多");
		codeMap.put("223118","质量控制项错误quality control error");
		codeMap.put("223119","活体控制项错误liveness control item error");
		codeMap.put("223120","活体检测未通过liveness check fail");
		codeMap.put("223121","质量检测未通过 左眼遮挡程度过高");
		codeMap.put("223122","质量检测未通过 右眼遮挡程度过高");
		codeMap.put("223123","质量检测未通过 左脸遮挡程度过高");
		codeMap.put("223124","质量检测未通过 右脸遮挡程度过高");
		codeMap.put("223125","质量检测未通过 下巴遮挡程度过高");
		codeMap.put("223126","质量检测未通过 鼻子遮挡程度过高");
		codeMap.put("223127","质量检测未通过 嘴巴遮挡程度过高");

		codeMap.put("222901","系统繁忙");
		codeMap.put("222902","系统繁忙");
		codeMap.put("222903","系统繁忙");
		codeMap.put("222904","系统繁忙");
		codeMap.put("222905","系统繁忙");
		codeMap.put("222906","系统繁忙");
		codeMap.put("222907","系统繁忙");
		codeMap.put("222908","系统繁忙");
		codeMap.put("222909","系统繁忙");
		codeMap.put("222910","系统繁忙");
		codeMap.put("222911","系统繁忙");
		codeMap.put("222912","系统繁忙");
		codeMap.put("222913","系统繁忙");
		codeMap.put("222914","系统繁忙");
		codeMap.put("222915","系统繁忙");
		codeMap.put("222916","系统繁忙");
		codeMap.put("222361","系统繁忙");

		codeMap.put("222350","公安网图片不存在或质量过低");
		codeMap.put("222351","身份证号与姓名不匹配或该身份证号不存在");
		codeMap.put("222352","身份证名字格式错误");
		codeMap.put("222353","身份证号码格式错误");
		codeMap.put("222354","公安库里不存在此身份证号");
		codeMap.put("222355","身份证号码正确，公安库里没有对应的照片");
		codeMap.put("222360","身份证号码或名字非法（公安网校验不通过）");


		codeMap.put("216015","模块关闭");
		codeMap.put("216100","非法参数");
		codeMap.put("216101","参数数量不够");
		codeMap.put("216102","业务不支持");
		codeMap.put("216103","参数太长");
		codeMap.put("216110","APP ID不存在");
		codeMap.put("216111","非法用户ID");
		codeMap.put("216200","空的图片");
		codeMap.put("216201","图片格式错误");
		codeMap.put("216202","图片大小错误");
		codeMap.put("216300","DB错误");
		codeMap.put("216400","后端系统错误");
		codeMap.put("216401","内部错误");
		codeMap.put("216500","未知错误");
		codeMap.put("282000","业务逻辑层内部错误");
		codeMap.put("282001","业务逻辑层后端服务错误");
		codeMap.put("282002","请求参数编码错误");
		codeMap.put("282100","图片压缩转码错误");
		codeMap.put("282202","检测超时");
		codeMap.put("282203","gif单帧大小超限");
		codeMap.put("282204","gif总帧数超限");
		codeMap.put("282205","图片格式错误");
		codeMap.put("282800","配置Id不存在");
		codeMap.put("282801","image和imgUrl均为空");
		codeMap.put("282802","image和imgUrl只能有一个有值");
		codeMap.put("282804","图片下载失败");
		codeMap.put("282805","调用底层服务异常");
		codeMap.put("282806","图片宽高异常");

	}
	
	public static String errMsg(Integer errCode){
		if(errCode != null && codeMap.containsKey(errCode+"")){
			return codeMap.get(errCode+"");
		}
		return null;
	}
	
}
