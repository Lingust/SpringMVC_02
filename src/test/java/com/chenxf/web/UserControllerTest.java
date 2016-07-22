package com.chenxf.web;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.context.WebApplicationContext;

public class UserControllerTest extends JUnitActionBase {
	
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
		MvcResult result = mockMvc.perform(get("/login?userName=admin&passwd=admin"))
					//.param("username", "admin")
					//.param("passwd", "admin"))	//perform执行一个请求
				.andExpect(view().name("success"))			//andExpect添加断言
				.andExpect(model().attributeExists("username"))		
				//.andDo(print())								//andDo添加结果处理器
				.andReturn();		//andReturn表示执行完成后返回相应的结果
		MvcResult result2 = mockMvc.perform(get("/checkUsername?userName=admin"))
				.andExpect(status().isOk())
				.andExpect(content().string("true"))
				.andReturn();
		MvcResult result3 = mockMvc.perform(get("/checkUsername?userName=adm"))
				.andExpect(status().isOk())
				.andExpect(content().string("false"))
				.andReturn();
	}
	
	//@Test
	public void testRegisterUser() throws Exception {
		MvcResult result = mockMvc.perform(post("/registUser")
					.param("userName", "chenc")
					.param("passwd", "test"))
				.andExpect(status().isOk())
				.andExpect(content().string("forward:/success"))	//在要测试的方法上加@ResponseBody即可测试通过
				.andReturn();
	}
}
