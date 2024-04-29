package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{

	//public abastract pēc nosklusējuma
	//izfiltŗēs produktu, kuram sakritīs ieejas 
	//argumentos norādītais nosaukums, apraksts un cena
	Product findByTitleAndDescriptionAndPrice
		(String title, String description, float price);

}
