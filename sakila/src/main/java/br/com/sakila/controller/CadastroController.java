package br.com.sakila.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sakila.mail.Mailer;
import br.com.sakila.mail.Mensagem;
import br.com.sakila.model.UsuarioModel;
import br.com.sakila.repository.UsuarioRepository;

@Controller
public class CadastroController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Mailer mailer;
	
	@GetMapping({"/cadastro/recuperar_senha"})
    public ModelAndView recuperarSenha(UsuarioModel usuario) { 	
		ModelAndView mv = new ModelAndView("cadastro/recuperar_senha");
        return mv;  	
    	    	
    }
	@PostMapping({"/cadastro/recuperar_senha"})
    public ModelAndView recuperarSenhaEmail(UsuarioModel usuario,Model model,RedirectAttributes atributes) { 
		
	
		ModelAndView mv = new ModelAndView("cadastro/recuperar_senha");
			
		usuario = this.usuarioRepository.getOneByCpf("11111111111");
		
		
				this.mailer.enviar(new Mensagem("sakila - Etejk <noreplysakila@gmail.com>", 
				Arrays.asList("Recuperação de senha <"+ usuario.getEmail() +">")
				, "Recuperação de senha", "Olá! \n\n Sua nova senha é:  " + "novaSenha"  ));
				
				model.addAttribute("mensagem", "Senha enviada com sucesso para o email cadastrado.");
		
		
		
        return mv;
    	    	
    }


}
