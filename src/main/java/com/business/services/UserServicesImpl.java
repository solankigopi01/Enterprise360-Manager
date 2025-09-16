package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.business.entities.User;
import com.business.repositories.UserRepository;

@Service
public class UserServicesImpl implements IUserServices {


	@Autowired
	private UserRepository userRepo;

	
	@Override
	public List<User> getAllUser() {
		List<User> users=(List<User>) userRepo.findAll();
		return users;
	}

	@Override
	public User getUserById(int id) {
		Optional<User> opt=userRepo.findById(id);
		User user=opt.get();
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user=userRepo.findUserByUemail(email);
		return user;
	}

	@Override
	public String updateUserById(User user, int id) {
		Optional<User> opt=userRepo.findById(id);
		if(opt.isEmpty()) {
			return "User is not exist with this id...";
		}
		else {
			userRepo.save(user);
			return "User is updated...";
		}
	}

	@Override
	public String deleteUserById(int id) {
		Optional<User> opt=userRepo.findById(id);
		if(opt.isEmpty()) {
			return "User is not exist with this id...";
		}
		else {
			userRepo.deleteById(id);
			return "User is deleted...";
		}
	}

	@Override
	public String addUser(User user) {
		userRepo.save(user);
		return "New user is added...";
	}

	@Override
	public boolean validateLoginCredentials(String email, String password) {
		List<User> users = (List<User>) userRepo.findAll();
		for(User u:users)
		{
		if(u!=null && u.getUpassword().equals(password) && u.getUemail().equals(email))
		{
			return true;
		}
		}
		return false;
	}

}
