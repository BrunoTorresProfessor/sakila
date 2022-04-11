package br.com.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.sakila.repository.LanguageRepository;

@Controller
public class LanguageController {

	@Autowired
	private LanguageRepository languageRepository;
	
	@GetMapping({"/idiomas/idiomas"}) //é o nome que eu quiser colocar
    public String listarIdiomas(ModelMap model) { 			
    	   
		 //o findAll lista todos atores 
		 model.addAttribute("idiomas",languageRepository.findAll());
		 return "idiomas/idiomas"; //é o nome do arquivo real
    	    	
	}
}	