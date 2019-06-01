/**
 * (C) Artur Boronat, 2017
 */
package eMarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrderController {

	@RequestMapping("/system/user")
	public String user() {
		return "indexUser";
	}

	@RequestMapping("/system/premium")
	public String premium() {
		return "indexPremium";
	}

	@RequestMapping("/order")
    public String order() {
        return "form/order";
    }
	
	@RequestMapping("/order/wishlist")
	public String wishlist() {
		return "form/wishlist";
	}
    
}
