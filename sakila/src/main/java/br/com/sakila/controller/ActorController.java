package br.com.sakila.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sakila.model.ActorModel;
import br.com.sakila.repository.ActorRepository;

@Controller
public class ActorController {
	
	//cria uma instãncia do nosso repositório
	@Autowired
	private ActorRepository actorRepository;
	
	@GetMapping({"/"}) //é o nome que eu quiser colocar
    public String home(ModelMap model) { 			
    	   
		 Page<ActorModel> page = actorRepository.findAll(PageRequest.of(0, 10));	
		 
			
		 model.addAttribute("atores",page);
		
		 int totalPages = page.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                    .boxed()
	                    .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
		 
		 return "atores/atores"; //é o nome do arquivo real	
    	    	
    }
	
	@GetMapping({"/atores/atores"}) //é o nome que eu quiser colocar
    public String listarAtores(ModelMap model) { 			
    	   
		 Page<ActorModel> page = actorRepository.findAll(PageRequest.of(0, 10));		 
			
		 model.addAttribute("atores",page);
		
		 int totalPages = page.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                    .boxed()
	                    .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
		 
		 return "atores/atores"; //é o nome do arquivo real	
    	    	
    }
	@GetMapping({"/atores/atores/{page}"}) //é o nome que eu quiser colocar
    public String listarAtoresPaginados(ModelMap model,@PathVariable("page") Integer pagina) { 			
    	   
		 Page<ActorModel> page = actorRepository.findAll(PageRequest.of(pagina-1, 10));		 
			
		 model.addAttribute("atores",page);
		
		 int totalPages = page.getTotalPages();
	        if (totalPages > 0) {
	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
	                    .boxed()
	                    .collect(Collectors.toList());
	            model.addAttribute("pageNumbers", pageNumbers);
	        }
		 
		 return "atores/atores"; //é o nome do arquivo real	
    	    	
    }
	
	@GetMapping({"/atores/enderecos"}) //é o nome que eu quiser colocar
    public String listarEnderecos(ModelMap model) { 			
   
		 return "atores/enderecos"; //é o nome do arquivo real	
    	    	
    }


}
