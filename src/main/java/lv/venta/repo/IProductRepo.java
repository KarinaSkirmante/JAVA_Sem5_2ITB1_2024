package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{

	//public abstract pēc nosklusējuma
	//izfiltŗēs produktu, kuram sakritīs ieejas 
	//argumentos norādītais nosaukums, apraksts un cena
	Product findByTitleAndDescriptionAndPrice
		(String title, String description, float price);

	
	//public abstract pēc nosklusējuma
	//izfiltrēs visus produktus, kuru cena ir mazaka par norādīto argumentu
	//SELECT * FROM PRODUCT_TABLE WHERE PRICE <=  priceThreshold
	ArrayList<Product> findByPriceLessThanEqual(float priceThreshold);
	
	//public abstract pēc nosklusējuma
	//izfiltrēs visus produktus, kuru daudzums ir mazaks par norādīto argumentu
	//SELECT * FROM PRODUCT_TABLE WHERE QUANTITY <=  quantityThreshold
	ArrayList<Product> findByQuantityLessThanEqual(int quantityThreshold);


	//public abstract pēc nosklusējuma
	//izfiltrēs visus produktus, kuru nosaukums vai apraksts satur konkrēto tesktu (1. un 2. argumns ir vienāds izsaukumā)
	//SELECT * FROM PRODUCT_TABLE WHERE UPPER(TITLE) LIKE  "%UPPER(1.arguments)%" OR UPPER(DESCRIPTION) LIKE "%UPPER(2.arguments)%"
	ArrayList<Product> findByTitleLikeIgnoreCaseOrDescriptionLikeIgnoreCase(String searchText, String searchText2);

	//public abstract pēc nosklusējuma
	@Query(nativeQuery = true, value = "SELECT SUM(PRICE * QUANTITY) FROM PRODUCT_TABLE;")
	float calculateTotalValueOfDBProducts();

}
