/**
 * (C) Artur Boronat, 2016
 */
package eMarket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eMarket.domain.UserInfo;
import eMarket.domain.Role;
import eMarket.repository.RoleRepository;
import eMarket.repository.UserInfoRepository;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired UserInfoRepository userInfoRepo;
	@Autowired RoleRepository roleRepo;
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserInfoValidator());
    }
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String signup(@ModelAttribute("userInfo") UserInfo userInfo, Model model) {
    		// prepare view
    		//    		model.addAttribute("userTypeValues",UserType.values());
    		List<Role> list = ((List<Role>) roleRepo.findAll());
    		// 	create a list with the name of each role
    		//			Without lambda expressions...
    		List<String> nameList = new ArrayList<>();
    		for (Role r: list) {
    			nameList.add(r.getRole());
    		}
    		model.addAttribute("userTypeValues", nameList);
    		//
    		// or equivalently with lambda expressions...
//    		model.addAttribute("userTypeValues",list.stream().map(r -> r.getRole()).collect(Collectors.toList()));
    		return "Signup";
    }
    
    @RequestMapping(value="add", params = "add", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("userInfo") UserInfo userInfo, BindingResult result, Model model) {
    		if (result.hasErrors()) {
    			// there are validation errors

    			// prepare view
//    			model.addAttribute("userTypeValues",UserType.values());
    			List<Role> list = ((List<Role>) roleRepo.findAll());
    			List<String> nameList = new ArrayList<>();
        		for (Role r: list) {
        			nameList.add(r.getRole());
        		}
        		model.addAttribute("userTypeValues", nameList);
//    			model.addAttribute("userTypeValues",list.stream().map(r -> r.getRole()).collect(Collectors.toList()));
    			return "Signup";
    		} else {
    			// no validation errors

    			// back-end logic
    			System.out.println("entered: " + userInfo.toString());

    			// get the user role name from the enum literal chosen
    			String userRole = userInfo.getUserType();
    			// we set the role from the information chosen about the user type (enumeration)
    			userInfo.setRole(roleRepo.findByRole(userRole));
    			
    			// encrypt password
    			BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
    			userInfo.setPassword(pe.encode(userInfo.getPassword()));
    			userInfo.setPassword(userInfo.getPassword());
    			
    			// save in repo
    			userInfoRepo.save(userInfo);
        	
    			// prepare view
    			return "redirect:/login-form";
    		}
    }
    
    @RequestMapping(value="add", params = "cancel", method = RequestMethod.POST)
    public String cancelNewUser(@ModelAttribute("userInfo") UserInfo userInfo, Model model) {
    		return "redirect:/login-form";
    }

}
