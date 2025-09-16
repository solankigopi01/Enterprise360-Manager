package com.business.services;

import java.util.List;

import com.business.entities.Admin;

public interface IAdminServices {
public List<Admin> getAll();
public Admin getAdminById(int id);
public String updateAdminById(int id, Admin admin);
public String deleteAdminById(int id);
public String addAdmin(Admin admin);
public boolean validateAdminCredentials(String email,String password);

}
