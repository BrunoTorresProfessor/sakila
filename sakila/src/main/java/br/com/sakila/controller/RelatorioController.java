package br.com.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.sakila.repository.ActorRepository;

@Controller
public class RelatorioController {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@GetMapping({"/relatorios/listar_atores"}) //é o nome que eu quiser colocar
    public String listarAtores(ModelMap model) { 			
    	   
		 model.addAttribute("atores",actorRepository.findAll());
		 return "relatorios/rel_listar_atores"; //é o nome do arquivo real	
    	    	
    }
	

}
