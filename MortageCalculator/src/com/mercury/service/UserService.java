package com.mercury.service;

import java.util.ArrayList;
import java.util.Collection;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.dao.UserDao;

@Service
@Transactional(readOnly=true)
public class UserService implements UserDetailsService{
	private UserDao ud;
	
	public UserDao getUd(){
		return ud;
	}
	
	public void setUd(UserDao ud){
		this.ud = ud;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userdetails =null;
		try {
		com.mercury.beans.User user = ud.findUserByName(username);
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
		userdetails = new User(
				user.getUsername(),
				user.getPassword(),
				true,
				true,
				true,
				true,
				authorities 
				);
	} catch (Exception e) {
		e.printStackTrace();
		throw new UsernameNotFoundException("Error in retrieving user");
	}
	return userdetails;
		
	}

}
