package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.business.entities.Product;
import com.business.repositories.ProductRepository;

@Service
public class ProductServicesImpl implements IProductService {


	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public void addProduct(Product prod) {
		productRepo.save(prod);
		
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products=(List<Product>) productRepo.findAll();
		return products;
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> opt=productRepo.findById(id);
		Product prod=opt.get();
		return prod;
	}

	@Override
	public String updateProductById(int id, Product prod) {
		Optional<Product> opt=productRepo.findById(id);
		if(opt.isEmpty()) {
			return "Product does not exist with this id";
		}
		else {
			productRepo.save(prod);
			return "Product is updated";
		}
		
	}

	@Override
	public String deleteProductById(int id) {
		Optional<Product> opt=productRepo.findById(id);
		if(opt.isEmpty()) {
			return "Product does not exist with this id";
		}
		else {
			productRepo.deleteById(id);;
			return "Product is deleted";
		}
	}

	@Override
	public Product getProductByName(String name) {
		Product product= productRepo.findByPname(name);
		if(product!=null)
		{
			return product;
		}
		return null;
	
	}

}
