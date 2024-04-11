package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Controller
public class FirstController {

	@Autowired
	private ICRUDProductService crudService;

	@Autowired
	private IFilterProductService filterService;

	@GetMapping("/hello") // localhost:8080/hello
	public String getHello() {
		System.out.println("Hello from Spring boot");
		return "hello-page"; // tiek parādīta hello-page.html lapa
	}

	@GetMapping("/hello/msg") // localhost:8080/hello/msg
	public String getHelloMsg(Model model) {
		// nosaukums, vērtība
		model.addAttribute("mymsg", "Ziņa no backend!! Hei!");
		return "msg-page"; // tiek parādīta msg-page.html lapa
	}

	@GetMapping("/product/test") // localhost:8080/product/test
	public String getProductTest(Model model) {
		try {
			model.addAttribute("myobj", crudService.retrieveAll().get(0));
			return "show-product-page"; // tiek parādīta show-product-page.html lapa
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}

	}

	// get mapping funkciju, kura pados sarakstu no vismaz 3 produktiem
	// html kods, kas so sarakstu avr parādīt

	@GetMapping("/product/all") // localhost:8080/product/all
	public String getProductAll(Model model) {
		try
		{
			model.addAttribute("myobjs", crudService.retrieveAll());
			return "show-product-all-page"; // tiek parādīta show-product-all-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
		}

	@GetMapping("/productone") // localhost:8080/productone?id=2
	public String getProductoneId(@RequestParam("id") int id, Model model) {

		try
		{
			model.addAttribute("myobj", crudService.retrieveById(id));
			return "show-product-page";// tiek parādīta show-product-pahe.html lapa
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}

	}

	@GetMapping("/product/all/{id}") // localhost:8080/product/all/2
	public String getProductAllId(@PathVariable("id") int id, Model model) {

		try
		{
			model.addAttribute("myobj", crudService.retrieveById(id));
			return "show-product-page";// tiek parādīta show-product-pahe.html lapa
		}
		catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}

	}
	
	
	//TODO
	//1. izveido getMapping funkciju, kas atvērs html lapu
	//2. izveidojam html lapu, kura būs 4 ievades lauki un viena submit poga
	//3. izveido postmapping funkciju, kura nolasa Product, aks nāk no html 
	//un ar servisa palīdzību saglabā

}
