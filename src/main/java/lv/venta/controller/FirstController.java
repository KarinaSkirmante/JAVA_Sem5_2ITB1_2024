package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
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
		try {
			model.addAttribute("myobjs", crudService.retrieveAll());
			return "show-product-all-page"; // tiek parādīta show-product-all-page.html lapa
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	}

	@GetMapping("/productone") // localhost:8080/productone?id=2
	public String getProductoneId(@RequestParam("id") int id, Model model) {

		try {
			model.addAttribute("myobj", crudService.retrieveById(id));
			return "show-product-page";// tiek parādīta show-product-pahe.html lapa
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}

	}

	@GetMapping("/product/all/{id}") // localhost:8080/product/all/2
	public String getProductAllId(@PathVariable("id") int id, Model model) {

		try {
			model.addAttribute("myobj", crudService.retrieveById(id));
			return "show-product-page";// tiek parādīta show-product-pahe.html lapa
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}

	}

	@GetMapping("/product/insert") // localhost:8080/product/insert
	public String getProductInsert(Model model) {
		model.addAttribute("product", new Product());// padodam tuksu produktu, kuru aizpildīs html pusē
		return "insert-product-page";// tiek parādīta insert-product-page.html lapa, kurā varēs ievadīt datus
	}

	@PostMapping("/product/insert")
	public String postProductInsert(@Valid Product product, BindingResult result) {// iegūst aizpildītu produktu

		if (result.hasErrors()) {
			return "insert-product-page";
		} else {

			try {
				crudService.create(product);
				return "redirect:/product/all";
			} catch (Exception e) {
				return "redirect:/error";// pārlec uz citu endpointu
			}
		}

	}

	@GetMapping("/error") // localhost:8080/error
	public String getError() {
		return "error-page";
	}


	
	@GetMapping("/product/update") //localhost:8080/product/update?id=2
	public String getProductUpateById(@RequestParam("id") int id, Model model ) {
		
		try {
			Product product = crudService.retrieveById(id);
			model.addAttribute("product", product);
			model.addAttribute("id", id);
			return "update-product-page";//tiek parādīta update-product-page.html lapa
				
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
		
		
	}
	
	
	@PostMapping("/product/update")
	public String postProductInsert(@RequestParam("id") int id,  @Valid Product product, BindingResult result, Model model) {// iegūst redigēto produktu

		if (result.hasErrors()) {
			model.addAttribute("id", id);
			return "update-product-page";
		} else {

			try {
				crudService.updateById(id, product);
				return "redirect:/product/all/" + id;
			} catch (Exception e) {
				return "redirect:/error";// pārlec uz citu endpointu
			}
		}

	}
	
	
	@GetMapping("/product/delete") //localhost:8080/product/delete?id=2
	public String getProductDeleteById(@RequestParam("id") int id, Model model) {
		
		try {
			crudService.deleteById(id);
			model.addAttribute("myobjs", crudService.retrieveAll());
			return "show-product-all-page"; // tiek parādīta show-product-all-page.html lapa	
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
