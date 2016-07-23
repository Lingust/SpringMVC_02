package com.chenxf.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//@ResponseBody
	@RequestMapping(value="/registUser",method=RequestMethod.POST)
	public String registerUser(HttpServletRequest request,
								@Validated @ModelAttribute("user") TUser user, 
								BindingResult bindingResult,
								Model model){
		/*设置注册日期，设置IP，最后登录日期等*/
		if(bindingResult.hasErrors())
			return "fail";
		userService.insertUser(user);
		setSessionUser(request, user);
		return "success";
	}
	
	@ModelAttribute("user")
	public TUser getUser(){
		TUser user = new TUser();
		return user;
	}
	
	public String registerSuccess(){
		return null;
	}
	
	@RequestMapping(value="/checkUsername/{userName}",method=RequestMethod.GET)
	@ResponseBody
	public String checkUsername(HttpServletRequest request,@PathVariable("userName")String userName){
		TUser user1 = userService.getUserByUserName(userName);
		if(user1!=null && user1.getUserName().equals(userName)){
			return "true";
		} else {
			return "false";
		}
	}
	
}
