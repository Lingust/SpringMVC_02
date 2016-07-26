package com.chenxf.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxf.cons.CommonConstant;
import com.chenxf.domain.TUser;
import com.chenxf.service.UserService;

@Controller
public class LoginController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView userLogin(HttpServletRequest request, TUser user){
		TUser dbUser = userService.getUserByUserName(user.getUserName());
		ModelAndView mv = new ModelAndView();
		if(dbUser == null){
			mv.addObject("errorMsg", "用户名不存在");
		} else if(!dbUser.getPasswd().equals(user.getPasswd())) {
			mv.addObject("errorMsg", "用户密码不正确");
		} else if (dbUser.getClass() == null){		//此处后期补充相应字段
			mv.addObject("errorMsg", "用户已被锁定，不能登录");
		} else {
			/*获取上一次登录日期并返回前端*/
			/*设置相应的积分、IP、最后登录日期等*/
			setSessionUser(request, dbUser);
			String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
			if(StringUtils.isEmpty(toUrl)){
				toUrl = "/index.html";
			}
			//mv.setViewName("redirect:"+toUrl);
			//mv.addObject("username", dbUser.getUserName());
			mv.setViewName("success");
			return mv;
		}
		mv.setViewName("forward:/");	//注意，返回首页转发方式
		return mv;
	}
	@RequestMapping(value="/toUrl")
	public String toIndex(){
		return "forward:/index.jsp";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "forward:/index.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/download")
	public byte[] hadnle333() throws IOException{
		File file = new File("");
		//Resource res = new FileSystemResource(file);
		//byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
		byte[] fileData = FileCopyUtils.copyToByteArray(file);
		return fileData;
	}
	
}
