package com.business.services;

import java.util.List;

import com.business.entities.User;

public interface IUserServices {
	public List<User> getAllUser();
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public String updateUserById(User user,int id);
	public String deleteUserById(int id);
	public String addUser(User user);
	public boolean validateLoginCredentials(String email,String password);
}
