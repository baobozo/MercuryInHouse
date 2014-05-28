package com.mercury.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.User;
import com.mercury.dao.UserDao;
import com.mercury.dao.UserDaoImpl;
@Controller
public class ProfileController {
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
		String username=securityContextImpl.getAuthentication().getName();
		UserDaoImpl udi = new UserDaoImpl();
		User user = udi.findUserInfoByName(username);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("profile");
		mav.addObject("username", user.getUsername());
		mav.addObject("passowrd", user.getPassword());
		mav.addObject("email", user.getEmail());
		mav.addObject("photo",user.getPhoto());
		return mav;
		
	}
}
