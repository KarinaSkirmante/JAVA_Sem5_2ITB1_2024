package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;



@Controller
public class FirstController {
	
	Product myProduct1 = new Product("Abols", "Sarkans", 0.99f, 5);
	Product myProduct2 = new Product("Zemene", "Salda", 1.23f, 3);
	Product myProduct3 = new Product("Arbuzs", "Roza", 3.99f, 2);
	ArrayList<Product> allProducts = 
			new ArrayList<>(Arrays.asList(myProduct1,myProduct2, myProduct3 ));

	
	
	@GetMapping("/hello") //localhost:8080/hello
	public String getHello() {
		System.out.println("Hello from Spring boot");
		return "hello-page"; //tiek parādīta hello-page.html lapa
	}
	
	
	@GetMapping("/hello/msg")//localhost:8080/hello/msg
	public String getHelloMsg(Model model) {
		//                  nosaukums, vērtība
		model.addAttribute("mymsg"   , "Ziņa no backend!! Hei!");
		return "msg-page"; //tiek parādīta msg-page.html lapa
	}
	
	
	@GetMapping("/product/test")//localhost:8080/product/test
	public String getProductTest(Model model) {
		Product myProduct = new Product("Abols", "Sarkans", 0.99f, 5);
		model.addAttribute("myobj", myProduct);
		return "show-product-page"; //tiek parādīta show-product-page.html lapa
	}
	
	//get mapping funkciju, kura pados sarakstu no vismaz 3 produktiem
	//html kods, kas so sarakstu avr parādīt
	
	@GetMapping("/product/all")//localhost:8080/product/all
	public String getProductAll(Model model) {
		model.addAttribute("myobjs", allProducts);
		return "show-product-all-page"; //tiek parādīta show-product-all-page.html lapa
	}
	
	
	
	@GetMapping("/productone")//localhost:8080/productone?id=2
	public String getProductoneId(@RequestParam("id") int id, Model model) {
		
		for(Product tempP: allProducts) {
			if(tempP.getId() == id) {
				model.addAttribute("myobj", tempP);
				return "show-product-page";//tiek parādīta show-product-pahe.html lapa
			}
		}
		
		model.addAttribute("msg", "Wrong id");
		return "error-page"; //tiek parādīta error-page.html lapa
		
	}
	
	@GetMapping("/product/all/{id}")//localhost:8080/product/all/2
	public String getProductAllId(@PathVariable("id") int id, Model model) {
		
		for(Product tempP: allProducts) {
			if(tempP.getId() == id) {
				model.addAttribute("myobj", tempP);
				return "show-product-page";//tiek parādīta show-product-pahe.html lapa
			}
		}
		
		model.addAttribute("msg", "Wrong id");
		return "error-page"; //tiek parādīta error-page.html lapa
		
	}

}
