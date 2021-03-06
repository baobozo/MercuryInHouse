package com.mercury.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public ModelAndView mainPage(HttpServletRequest request) {
		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
		String username=securityContextImpl.getAuthentication().getName();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("title", "Hello, welcome to Customized Spring Security");
		return mav;
	}
	
	
	
}
