/**
 * (C) Artur Boronat, 2015
 */
package eMarket.controller;

import java.util.List;

import javax.validation.Valid;

import eMarket.EMarketApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eMarket.domain.Product;
import eMarket.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired ProductRepository productRepo;
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    		binder.addValidators(new ProductValidator());
    }
    
    @RequestMapping("/")
    public String index(Model model) {
//    		model.addAttribute("productList", EMarketApp.getStore().getProductList());
    	model.addAttribute("productList", (List<Product>) productRepo.findAll());
        return "form/productMaster";
    }
    
    @RequestMapping(value = "/productDetail", method = RequestMethod.GET)
    public String productDetail(@ModelAttribute("product") Product product, BindingResult result, @RequestParam(value="productId", required=false, defaultValue="-1") int productId) {
    		if (!result.hasErrors()) {
		    	if (productId >= 0) {
		    		// modify
//		    		Product p2 = EMarketApp.getStore().getProductList().stream().filter(p -> (((Product) p).getId() == productId)).findAny().get();
		    		Product p2 = ((List<Product>) productRepo.findAll()).stream().filter(p -> (((Product) p).getId() == productId)).findAny().get();
		    		product.setId(p2.getId());
		    		product.setName(p2.getName());
		    		product.setDescription(p2.getDescription());
		    		product.setPrice(p2.getPrice());

		    	} else {
		    		// add
		    	//	product.setId();
		    	
		    	}
		}
	    	return "form/productDetail";
    }   
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String productMaster(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    		return "form/productDetail";
	    } else {
//		    	EMarketApp.getStore().getProductList().removeIf(p -> (p.getId() == product.getId()));
//	    		Product p = productRepo.findById(product.getId());
//	    		if (p != null) productRepo.delete(p);
//		    	EMarketApp.getStore().getProductList().add(product);
		    	productRepo.save(product);
		   		
		   // 	model.addAttribute("productList", EMarketApp.getStore().getProductList());
		    	model.addAttribute("productList", (List<Product>)productRepo.findAll());
		    	return "form/productMaster";
	    }
//    	return "redirect:/product/";
    }   

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String productMaster(@Valid @RequestParam(value="productId", required=false, defaultValue="-1") int productId, Model model) {
	  //  	EMarketApp.getStore().getProductList().removeIf(p -> (p.getId() == productId));
	    	Product p = productRepo.findById(productId);
	    	if (p != null) productRepo.delete(p);
	    //	model.addAttribute("productList", EMarketApp.getStore().getProductList());
	    	model.addAttribute("productList", (List<Product>) productRepo.findAll());
	    	return "form/productMaster";
    }   
    
    
    
}
