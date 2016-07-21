package com.chenxf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxf.domain.TUser;
import com.chenxf.service.UserService;

@Controller
public class UserLogin {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public ModelAndView userLogin(String username, String passwd){
		ModelAndView mv;
		TUser user = userService.getUserByUserName(username);
		if(user!=null && user.getPasswd().equals(passwd)){
			mv = new ModelAndView("success");
			mv.addObject("name", username);
			return mv;
		}
		mv = new ModelAndView("redirect:/index.jsp");
		return mv;
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
