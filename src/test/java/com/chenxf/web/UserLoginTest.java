package com.chenxf.web;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class UserLoginTest extends JUnitActionBase {
	
	//模拟request，response
	private MockHttpServletRequest requests;
	private MockHttpServletResponse response;
	
	//注入userLogin
	@Autowired
	private UserLogin userLogin;
	
	//执行测试方法前初始化模拟request，response
	@Before
	public void setUp(){
		requests = new MockHttpServletRequest();
		requests.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
	
	/**
	 * @Title testUserLogin
	 * @Descriptio 测试用户登录操作
	 * @author Chenxf
	 * @since 2016年7月19日 20时10分
	 */
	@Test
	public void testUserLogin() throws Exception{
		String username = "陈小峰";
		String passwd = "1234";
		assertEquals("success",userLogin.userLogin(username, passwd).getViewName());
	}
}
