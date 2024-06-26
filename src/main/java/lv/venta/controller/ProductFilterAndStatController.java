package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.model.Product;
import lv.venta.service.IFilterProductService;

@Controller
@RequestMapping("/product/info")
public class ProductFilterAndStatController {

	@Autowired
	private IFilterProductService filterService;
	
	@GetMapping("/filter/price") //localhost:8080/product/info/filter/price?threshold=0.99
	public String getProductInfoFilterByPrice(@RequestParam("threshold") float threshold,
			Model model)
	{
		try {
			ArrayList<Product> result = filterService.filterProductByPriceThreshold(threshold);
			model.addAttribute("myobjs", result);
			model.addAttribute("mytitle", "Products filtered by price");
			return "show-product-all-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	
	}
	
	@GetMapping("/filter/quantity") //localhost:8080/product/info/filter/quantity?threshold=4
	public String getProductInfoFilterByQuantity(@RequestParam("threshold") int threshold,
			Model model)
	{
		try {
			ArrayList<Product> result = filterService.filterProductByQuantityThreshold(threshold);
			model.addAttribute("myobjs", result);
			model.addAttribute("mytitle", "Products filtered by quantity");
			return "show-product-all-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	
	}
	
	@GetMapping("/filter/text") //localhost:8080/product/info/filter/text?keyword=Sa
	public String getProductInfoFilterByTitleOrDescriptionText(@RequestParam("keyword") String keyword,
			Model model)
	{
		try {
			ArrayList<Product> result = filterService.filterByTitleOrDescription(keyword);
			model.addAttribute("myobjs", result);
			model.addAttribute("mytitle", "Products filtered by title and description");
			return "show-product-all-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	
	}
	
	@GetMapping("/total") //localhost:8080/product/info/total
	public String getProductInfoFilterByTitleOrDescriptionText(Model model)
	{
		try {
			float result = filterService.calculateProductsTotalValue();
			model.addAttribute("mymsg", "Total: " + result + " eur");//TODO noformatē tikai uz 2 cipariem aiz komata
			return "msg-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page"; // tiek parādīta error-page.html lapa
		}
	
	}
	
}
