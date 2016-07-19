package com.chenxf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLogin {
	
	@RequestMapping(value="/login")
	public ModelAndView userLogin(String username, String passwd){
		ModelAndView mv;
		if(username.equals("陈小峰") && passwd.equals("1234")){
			mv = new ModelAndView("success");
			mv.addObject("name", username);
			mv.addObject("password", passwd);
			return mv;
		}
		mv = new ModelAndView("redirect:/index.jsp");
		return mv;
	}
}
