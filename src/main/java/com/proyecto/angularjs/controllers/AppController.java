package com.proyecto.angularjs.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class AppController {

	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	String home(HttpServletRequest request, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String usuario=auth.getName();
		model.addAttribute( "usuarioLogin",usuario);

		
		
		
		
		return "index";
	}

	@RequestMapping("/partials/modules/{module}/{page}")
	String partialHandler(@PathVariable("module") final String module, @PathVariable("page") final String page) {
		return "modules/" + module + "/" + page;
	}
}
