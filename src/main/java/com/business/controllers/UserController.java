package com.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.business.entities.User;
import com.business.services.UserServicesImpl;

@Controller
public class UserController
{
	@Autowired
	private UserServicesImpl services;

	@PostMapping("/addingUser")
	public String  addUser(@ModelAttribute User user)
	{
		System.out.println(user);
		this.services.addUser(user);
		return "redirect:/admin/services";
	}

	@GetMapping("/updatingUser/{id}")
	public String updateUser(@ModelAttribute User user, @PathVariable int id)
	{
		this.services.updateUserById(user, id);
		return "redirect:/admin/services";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id)
	{
		this.services.deleteUserById(id);
		return "redirect:/admin/services";
	}

	@GetMapping("/register")
	public String registerUserShowForm(@ModelAttribute("userRegistration") User user) {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute("userRegistration") User user, RedirectAttributes attrs) {
		String msg=services.addUser(user);
		attrs.addFlashAttribute("resultMsg", msg);
		return "redirect:/login";
	}


}
