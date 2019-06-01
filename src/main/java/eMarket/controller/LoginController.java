/**
 * (C) Artur Boronat, 2016
 */
package eMarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eMarket.EMarketApp;
import eMarket.domain.UserInfo;
import eMarket.domain.UserInfoLogin;
import eMarket.repository.UserInfoRepository;

@Controller
public class LoginController {
	@Autowired UserInfoRepository userInfoRepo;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    		binder.addValidators(new UserInfoLoginValidator());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landing() {
    		return "Landing";
    }

    @RequestMapping(value = "/login-form", method = RequestMethod.GET)
    public String login(@ModelAttribute("userInfoLogin") UserInfoLogin userInfoLogin) {
    		return "login-form";
    }

    @RequestMapping(value = "/success-login", method = RequestMethod.GET)
    public String authenticate() {
    		System.out.println("enters /success-login");
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		authUser.getAuthorities().stream().forEach(c -> System.out.println (c));

		System.out.println("logging in as " + authUser.getUsername());
        UserInfo user = userInfoRepo.findByLogin(authUser.getUsername());
        String view;
        switch (user.getRole().getId()) {
	    	case EMarketApp.ADMIN:
	    		view = "redirect:/system/";
	    		break;
	    	case EMarketApp.PREMIUM:
	    		view = "redirect:/system/premium";
	    		break;
	    default: //	case EMarketApp.USER:
	    		view = "redirect:/system/user";
	    		break;
        }

		return view;
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
    	return "redirect:/login-form";
    }

}
