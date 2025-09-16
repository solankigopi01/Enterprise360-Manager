package com.business.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.business.basiclogics.Logic;
import com.business.entities.Admin;
import com.business.entities.Orders;
import com.business.entities.Product;
import com.business.entities.User;
import com.business.loginCredentials.AdminLogin;
import com.business.loginCredentials.UserLogin;
import com.business.services.AdminServicesImpl;
import com.business.services.OrderServicesImpl;
import com.business.services.ProductServicesImpl;
import com.business.services.UserServicesImpl;

@Controller
public class AdminController {
	@Autowired
	private UserServicesImpl services;
	@Autowired
	private AdminServicesImpl adminServices;
	@Autowired
	private ProductServicesImpl productServices;	
	@Autowired
	private OrderServicesImpl orderServices;

	private String email;
	private User user;

	// Validating login 
	@GetMapping("/adminLogin")
	public String  getAllData(  @ModelAttribute("adminLogin") AdminLogin login, Model model)
	{
		String email=login.getEmail();
		String password=login.getPassword();
		if(adminServices.validateAdminCredentials(email, password))
		{
			return "redirect:/admin/services";
		}
		else {
			model.addAttribute("error", "Invalid email or password");
			return "Login";
		}

	}

	@GetMapping("/userlogin")
	public String userLogin( @ModelAttribute("userLogin") UserLogin login,Model model)
	{

		email=login.getUserEmail();
		String password=login.getUserPassword();
		if(services.validateLoginCredentials(email, password))
		{
			user = this.services.getUserByEmail(email);
			List<Orders> orders = this.orderServices.getOrdersForUser(user);
			model.addAttribute("orders", orders);
			model.addAttribute("name", user.getUname());
			return "BuyProduct";
		}
		else
		{
			model.addAttribute("error2", "Invalid email or password");
			return "Login";
		}

	}
	//Searching Product By Name
	@PostMapping("/product/search")
	public String seachHandler(@RequestParam("productName") String name,Model model)
	{

		Product product=this.productServices.getProductByName(name);
		if(product==null)
		{
			model.addAttribute("message", "SORRY...!  Product Unavailable");
			model.addAttribute("product", product);
			List<Orders> orders = this.orderServices.getOrdersForUser(user);
			model.addAttribute("orders", orders);
			return "BuyProduct";
		}
		List<Orders> orders = this.orderServices.getOrdersForUser(user);
		model.addAttribute("orders", orders);
		model.addAttribute("product", product);
		return "BuyProduct";

	}

	//Providing services 
	@GetMapping("/admin/services")
	public String returnBack(Model model)
	{
		List<User> users= this.services.getAllUser();
		List<Admin>admins=this.adminServices.getAll(); 
		List<Product>products=this.productServices.getAllProducts();
		List<Orders> orders = this.orderServices.getAllOrders();
		model.addAttribute("users",users);
		model.addAttribute("admins", admins);
		model.addAttribute("products", products);
		model.addAttribute("orders", orders);

		return "Admin_Page";
	}

	//Invoking addAdmin Page
	@GetMapping("/addAdmin")
	public String addAdminPage()
	{
		return "Add_Admin";
	}

	//Handling AddAdmin
	@PostMapping("addingAdmin")
	public String addAdmin( @ModelAttribute Admin admin)
	{

		this.adminServices.addAdmin(admin);
		return "redirect:/admin/services";

	}

	//invoking updateAdmin Page
	@GetMapping("/updateAdmin/{adminId}")
	public String update(@PathVariable("adminId") int id,Model model)
	{
		Admin admin = this.adminServices.getAdminById(id);
		model.addAttribute("admin", admin);
		return "Update_Admin";
	}

	//Handling Update Page
	@GetMapping("/updatingAdmin/{id}")
	public String updateAdmin(@ModelAttribute Admin admin,@PathVariable int id)
	{
		this.adminServices.updateAdminById(id, admin);
		return "redirect:/admin/services";
	}

	//IHandling delete operation
	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable int id)
	{
		this.adminServices.deleteAdminById(id);
		return "redirect:/admin/services";
	}

	//Invoking AddProduct Page
	@GetMapping("/addProduct")
	public String addProduct()
	{
		return "Add_Product";
	}

	//Invoking Update Product Page
	@GetMapping("/updateProduct/{productId}")
	public String updateProduct(@PathVariable("productId") int id,Model model)
	{
		Product product=this.productServices.getProductById(id);
		System.out.println(product);
		model.addAttribute("product", product);
		return "Update_Product";
	}

	//Invoking AddUser Page
	@GetMapping("/addUser")
	public String addUser()
	{
		return "Add_User";
	}

	//Invoking UpdateUser Page
	@GetMapping("/updateUser/{userId}")
	public String updateUserPage(@PathVariable("userId") int id,Model model)
	{
		User user = this.services.getUserById(id);
		model.addAttribute("user", user);
		return "Update_User";
	}
	//Placing  Order
	@PostMapping("/product/order")
	public String orderHandler(@ModelAttribute() Orders order,Model model)
	{
		double  totalAmount = Logic.countTotal(order.getOPrice(),order.getOQuantity());
		order.setTotalAmmout(totalAmount);
		order.setUser(user);
		Date d=new Date();
		order.setOrderDate(d);
		this.orderServices.saveOrder(order);
		model.addAttribute("amount",totalAmount);
		return "Order_success";
	}

	@GetMapping("/product/back")
	public String back(Model model)
	{
		List<Orders> orders = this.orderServices.getOrdersForUser(user);
		model.addAttribute("orders", orders);
		return "BuyProduct";
	}

}
