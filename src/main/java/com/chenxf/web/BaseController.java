package com.chenxf.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.chenxf.cons.CommonConstant;
import com.chenxf.domain.TUser;

public class BaseController {
	
	/**
	 * 返回Session中存放的用户对象
	 * 登录前为空，登入后会存放对象，不会重复登录
	 * @param request
	 * @return
	 */
	protected TUser getSessionUser(HttpServletRequest request){
		return (TUser) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
	}
	
	/**
	 * 将登入对象存入Session中
	 * 后面进行操作时可以直接从此处获取对象
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request, TUser user){
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,
				user);
	}
	
	/**
	 * 获取基于应用程序的URL绝对路径
	 * @param request
	 * @param url
	 * @return
	 */
	public final String getAppBaseURL(HttpServletRequest request, String url){
		Assert.hasLength(url, "URL不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/开头");
		return request.getContextPath() + url;
	}
}
