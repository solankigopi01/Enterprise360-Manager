package com.business.services;

import java.util.List;

import com.business.entities.Product;

public interface IProductService {
	public void addProduct(Product prod);
	public List<Product> getAllProducts();
	public Product getProductById(int id);
	public String updateProductById(int id, Product prod);
	public String deleteProductById(int id);
	public Product getProductByName(String name);
}
