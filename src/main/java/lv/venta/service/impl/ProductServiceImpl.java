package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService{

	@Override
	public ArrayList<Product> filterProductByPriceThreshold(float priceThreshold) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String title, String description) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product create(Product product) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
