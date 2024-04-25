package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService{

	
	private ArrayList<Product> allProducts = new ArrayList<>(
			Arrays.asList(
					new Product("Abols", "Sarkans", 0.99f, 5),
					new Product("Zemene", "Salda", 1.23f, 3),
					new Product("Arbuzs", "Roza", 3.99f, 2)));
	
	@Override
	public Product create(Product product) throws Exception {
		if(product == null) throw new Exception("Product is null");
		
		for(Product tempP: allProducts) {
			if(tempP.getTitle().equals(product.getTitle()) &&
					tempP.getDescription().equals(product.getDescription()) &&
					tempP.getPrice() == product.getPrice()) {
				tempP.setQuantity(tempP.getQuantity() + product.getQuantity());
				return tempP;
			}
		}
		
		Product newProduct = new Product(product.getTitle(), product.getDescription(),
				product.getPrice(), product.getQuantity());
		allProducts.add(newProduct);
		return newProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(allProducts.isEmpty()) throw new Exception("Product list is empty");
		return allProducts;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if(id < 0) throw new Exception("Id should be positive");
		
		for(Product tempP: allProducts) {
			if(tempP.getId() == id) {
				return tempP;
			}
		}
		
		throw new Exception("Product with " + id + " is not found");
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		Product updateProduct = retrieveById(id);
		
		updateProduct.setTitle(product.getTitle());
		updateProduct.setDescription(product.getDescription());
		updateProduct.setQuantity(product.getQuantity());
		updateProduct.setPrice(product.getPrice());
				
	}

	@Override
	public Product deleteById(int id) throws Exception {
		Product deleteProduct = retrieveById(id);
		allProducts.remove(deleteProduct);
		return deleteProduct;	
	}

	@Override
	public ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception {
		if(priceThreshold < 0 || priceThreshold > 10000) throw new Exception("Wrong price threshold");
		
		ArrayList<Product> filteredProducts = new ArrayList<>();
		for(Product tempP: allProducts)
		{
			if(tempP.getPrice() <= priceThreshold) {
				filteredProducts.add(tempP);
			}
		}
		
		return filteredProducts;
		
	}

	@Override
	public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
		if(quantityThreshold < 0 || quantityThreshold > 100) throw new Exception("Wrong quantity threshold");
		
		ArrayList<Product> filteredProducts = new ArrayList<>();
		for(Product tempP: allProducts)
		{
			if(tempP.getQuantity() <= quantityThreshold) {
				filteredProducts.add(tempP);
			}
		}
		
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String searchText) throws Exception {
		if(searchText == null) throw new Exception("Wrong search text");
		ArrayList<Product> filteredProducts = new ArrayList<>();
		for(Product tempP: allProducts)
		{
			if(tempP.getTitle().contains(searchText) ||
					tempP.getDescription().contains(searchText)) {
				filteredProducts.add(tempP);
			}
		}
		
		return filteredProducts;	
	}

	@Override
	public float calculateProductsTotalValue() throws Exception {
		if(allProducts.isEmpty()) throw new Exception("There is no product in my web");

		float result = 0;
		for(Product tempP : allProducts) {
			result+=tempP.getPrice() * tempP.getQuantity();
		}
		return result;
	}

}
