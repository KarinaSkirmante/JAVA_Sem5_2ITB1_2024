package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;



@Controller
public class FirstController {
	
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
	
	
	
	

}
