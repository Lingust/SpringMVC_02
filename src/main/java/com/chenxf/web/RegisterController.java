package com.chenxf.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxf.domain.TUser;
import com.chenxf.service.UserService;

@Controller
public class RegisterController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	@RequestMapping("/registUser")
	public String registerUser(HttpServletRequest request, TUser user){
		/*设置注册日期，设置IP，最后登录日期等*/
		userService.insertUser(user);
		return "forward:/success";
	}
	
	public String registerSuccess(){
		return null;
	}
	
	@RequestMapping(value="/checkUsername",method=RequestMethod.GET)
	@ResponseBody
	public String checkUsername(String username){
		TUser user = userService.getUserByUserName(username);
		if(user!=null && user.getUserName().equals(username)){
			return "true";
		} else {
			return "false";
		}
	}
	
}
