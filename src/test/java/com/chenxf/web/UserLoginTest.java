package com.chenxf.web;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.context.WebApplicationContext;

public class UserLoginTest extends JUnitActionBase {
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	//模拟MVC测试环境
	@Before
	public void setUp(){
		mockMvc = webAppContextSetup(wac).build();
	}
	
	/**
	 * @Title testUserLogin
	 * @Descriptio 测试用户登录操作
	 * @author Chenxf
	 * @since 2016年7月19日 20时10分
	 */
	@Test
	public void testUserLogin() throws Exception{
		@SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(get("/login")
					.param("username", "admin")
					.param("passwd", "admin"))	//perform执行一个请求
				.andExpect(view().name("success"))			//andExpect添加断言
				.andExpect(model().attributeExists("name"))		
				//.andDo(print())								//andDo添加结果处理器
				.andReturn();		//andReturn表示执行完成后返回相应的结果
	}
}
