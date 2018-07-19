package com.proyecto.angularjs.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.services.UserService;

@Controller
public class AppController {

	private UserService Userservice;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	String home(HttpServletRequest request, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Object usuario=auth.getPrincipal();
		String usuario=auth.getName();
		model.addAttribute( "usuarioLogin",usuario);
		System.out.println(usuario);
		
		//User user=Userservice.findByName("admin");
		
		//System.out.println(user.getId_user());
		
		
		return "index";
	}

	@RequestMapping("/partials/modules/{module}/{page}")
	String partialHandler(@PathVariable("module") final String module, @PathVariable("page") final String page) {
		return "modules/" + module + "/" + page;
	}
}
