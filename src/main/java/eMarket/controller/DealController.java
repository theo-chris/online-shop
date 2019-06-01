/**
 * (C) Artur Boronat, 2017

 */
package eMarket.controller;

import javax.validation.Valid;

import eMarket.repository.DealRepository;
import eMarket.repository.ProductRepository;
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

import eMarket.EMarketApp;
import eMarket.domain.Deal;
import eMarket.domain.Product;

import java.util.List;

@Controller
@RequestMapping("/deal")
public class DealController {

	@Autowired ProductRepository productRepo;
	@Autowired DealRepository dealRepo;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

    	binder.addValidators(new DealValidator(dealRepo));
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
    		DealFormDto dto = new DealFormDto();
    		dto.setProductList((List<Product>)productRepo.findAll());
    		model.addAttribute("dealFormDto", dto);
    		model.addAttribute("dealList", (List<Deal>) dealRepo.findAll());
    		return "form/productDeal";
    }   

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String dealAdd(@Valid @ModelAttribute("dealFormDto") DealFormDto dealFormDto, BindingResult result, Model model) {
    		if (!result.hasErrors() ) {
	    		Deal d = new Deal();
	    		d.setId();
	    		d.setStartDate(dealFormDto.getStartDate());
	    		d.setEndDate(dealFormDto.getEndDate());
	    		Product p2 =((List<Product>)productRepo.findAll()).stream().filter(p -> (((Product) p).getId() == dealFormDto.getProductId())).findAny().get();
	    		d.setProduct(p2);
	    		d.setDiscount(dealFormDto.getDiscount());
				dealRepo.save(d);
    		
	    		DealFormDto dto = new DealFormDto();
	    		dto.setProductList((List<Product>)productRepo.findAll());
	    		model.addAttribute("dealFormDto", dto);
	    		model.addAttribute("dealList",(List<Deal>) dealRepo.findAll());
    		} else {
    			dealFormDto.setProductList((List<Product>) productRepo.findAll());
    			model.addAttribute("dealFormDto", dealFormDto);
    			model.addAttribute("dealList",(List<Deal>) dealRepo.findAll());
    		}
    		
    		return "form/productDeal";
    }   
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String dealDelete(@RequestParam(value="dealId", required=true, defaultValue="-1") int dealId, Model model) {
    		Deal d2 = ((List<Deal>) dealRepo.findAll()).stream().filter(d -> (((Deal) d).getId() == dealId)).findAny().get();
    		
    		if ((d2.getStartDate().isBefore(EMarketApp.getSystemDate())) || d2.getStartDate().isEqual(EMarketApp.getSystemDate())) {
    			System.out.println("closing deal");
	    		d2.close();
				dealRepo.save(d2);
    		}
    		
    		DealFormDto dto = new DealFormDto();
    		dto.setProductList((List<Product>)productRepo.findAll());
    		model.addAttribute("dealFormDto", dto);
    		model.addAttribute("dealList", (List<Deal>) dealRepo.findAll());

    		return "form/productDeal";
    }   
        
}
