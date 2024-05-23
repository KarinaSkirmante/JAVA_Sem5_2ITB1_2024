package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;

@RestController
@RequestMapping("/product")
public class ProductCRUDController {
	
	@Autowired
	private ICRUDProductService crudService;
	
	@GetMapping("/all") // localhost:8080/product/all
	public ResponseEntity getProductAll() {
		try {
			return new ResponseEntity<ArrayList<Product>>(crudService.retrieveAll(), HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/one") // localhost:8080/product/one?id=2
	public ResponseEntity getProductoneId(@RequestParam("id") int id) {

		try {
			return new ResponseEntity<Product>(crudService.retrieveById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@GetMapping("/all/{id}") // localhost:8080/product/all/2
	public ResponseEntity getProductAllId(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Product>(crudService.retrieveById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/insert")//localhost:8080/product/insert
	public ResponseEntity postProductInsert(@RequestBody @Valid Product product, BindingResult result) {// iegūst aizpildītu produktu

		if (result.hasErrors()) {
			return new ResponseEntity<String>("Problems with input params", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {

			try {
				crudService.create(product);
				return new ResponseEntity(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}

	}
	
	
	@GetMapping("/update") //localhost:8080/product/update?id=2
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
	
	
	@PostMapping("/update")
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
	
	
	@GetMapping("/delete") //localhost:8080/product/delete?id=2
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
