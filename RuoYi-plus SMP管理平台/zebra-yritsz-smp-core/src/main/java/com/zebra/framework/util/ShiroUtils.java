package com.zebra.framework.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.zebra.common.utils.StringUtils;
import com.zebra.common.utils.bean.BeanUtils;
import com.zebra.framework.shiro.realm.UserRealm;
import com.zebra.system.domain.SysUser;
import com.zebra.system.domain.page.SysUserPage;

/**
 * shiro 工具类
 *
 * @author ruoyi
 */
public class ShiroUtils {
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void logout() {
		getSubject().logout();
	}

	public static SysUserPage getSysUser() {
		SysUserPage user = null;
		Object obj = getSubject().getPrincipal();
		if (StringUtils.isNotNull(obj)) {
			user = new SysUserPage();
			BeanUtils.copyBeanProp(user, obj);
		}
		return user;
	}

	public static void setSysUser(SysUser user) {
		Subject subject = getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);
	}

	public static void clearCachedAuthorizationInfo() {
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
		realm.clearCachedAuthorizationInfo();
	}

	public static Long getUserId() {
		return getSysUser().getUserId().longValue();
	}

	public static List<Object> getObjs() {
		return getSysUser().getObjs() == null || getSysUser().getObjs().size() == 0 ? null : getSysUser().getObjs();
	}

	public static void setSysUserPage(SysUserPage sysUserPage) {
		Subject subject = getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(sysUserPage, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);
	}

	public static String getLoginName() {
		return getSysUser().getLoginName();
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

	public static String getSessionId() {
		return String.valueOf(getSubject().getSession().getId());
	}

	/**
	 * 生成随机盐
	 */
	public static String randomSalt() {
		// 一个Byte占两个字节，此处生成的3字节，字符串长度为6
		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		String hex = secureRandom.nextBytes(3).toHex();
		return hex;
	}
}
