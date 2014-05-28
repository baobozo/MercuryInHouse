package com.mercury.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mercury.beans.User;
import com.mercury.utils.JdbcUtil;

public class UserDaoImpl implements UserDao{
	private JdbcUtil util;
	
	public UserDaoImpl(){
		if(util==null){
			util = new JdbcUtil();
		}
	}

	
	@Override
	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		String sql ="select u.username, u.password, r.authority from users u, user_roles r where u.username=r.username and u.username =?";
		
		User user = new User();
		try {
			Connection conn = util.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAuthority(rs.getString("authority"));
			}
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getAuthority());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	
	public User findUserInfoByName(String username) {	
		String sql = "select * from users where username = ?";
		User user = new User();
		
		
		try {
			Connection conn = util.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhoto(rs.getString("photo"));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return user;
	}
	
}
