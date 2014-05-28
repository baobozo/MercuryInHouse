package com.mercury.dao;

import com.mercury.beans.User;

public interface UserDao {
	public User findUserByName(String name);
}
