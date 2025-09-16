package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Admin;
import com.business.repositories.AdminRepository;

@Service
public class AdminServicesImpl implements IAdminServices {

	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public List<Admin> getAll() {
		List<Admin> admins=(List<Admin>) adminRepo.findAll();
		return admins;
	}

	@Override
	public Admin getAdminById(int id) {
		Optional<Admin> optional=adminRepo.findById(id);
		Admin admin=optional.get();
		return admin;
	}

	@Override
	public String updateAdminById(int id, Admin admin) {
		Optional<Admin> opt=adminRepo.findById(id);
		if(opt.isEmpty()) {
			return "Admin is not present with this id";
		}
		else {
			adminRepo.save(admin);
			return "Admin is updated";
		}
	}

	@Override
	public String deleteAdminById(int id) {
		Optional<Admin> opt=adminRepo.findById(id);
		if(opt.isEmpty()) {
			return "Admin is not present with this id";
		}
		else {
			adminRepo.deleteById(id);
			return "Admin is deleted";
		}
	}

	@Override
	public String addAdmin(Admin admin) {
		adminRepo.save(admin);
		return "Admin is added...";
	}

	@Override
	public boolean validateAdminCredentials(String email, String password) {
		Admin admin=adminRepo.findByAdminEmail(email);
		if(admin!=null && admin.getAdminPassword().equals(password))
		{
			return true;
		}

		return false;
	}

}
