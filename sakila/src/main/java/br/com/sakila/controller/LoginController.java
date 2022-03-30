package br.com.sakila.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@GetMapping({"/login"}) //é o nome que eu quiser colocar
    public String login() { 			
    	   
		 return "login"; //é o nome do arquivo real	
    	    	
    }
	
	 @RequestMapping(value = {"/postlogin"}, method = {RequestMethod.POST})
	 public String postLogin(Model model, HttpSession session) {       
	       
	        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String cpf = auth.getName(); //recupera o login do usuario logado     
	        

	        session.setAttribute("cpf", cpf); //adiciona o login do usuário na sessão            
	        
	        return "atores/atores";

	    }  


}
