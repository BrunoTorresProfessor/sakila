package br.com.sakila.controller;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
			
		usuario = this.usuarioRepository.getOneByCpf(usuario.getCpf());
		
		String novaSenha = getRandomPass(); //gera uma senha randomica de 8 digitos
		
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		
		usuarioRepository.alterarSenha(b.encode(novaSenha),usuario.getCpf());
		        
				this.mailer.enviar(new Mensagem("sakila - Etejk <noreplysakila@gmail.com>", 
				Arrays.asList("Recuperação de senha <"+ usuario.getEmail() +">")
				, "Recuperação de senha", "Olá! \n\n Sua nova senha é:  " + novaSenha  ));
				
				model.addAttribute("mensagem", "Senha enviada com sucesso para o email cadastrado.");
		
		
		
        return mv;
    	    	
    }
	
	
	
	@GetMapping(value="/administracao/listar_usuarios")
    public ModelAndView ListarUsuarios(ModelMap model) { 	
		//Lista todos os usuários
		ModelAndView mv = new ModelAndView("/administracao/listar_usuarios");		

		
		model.addAttribute("usuarios", this.usuarioRepository.findAll());
		
        return mv;  	
    	    	
    }
	@GetMapping(value="/administracao/cadastrar_usuario")
    public ModelAndView CadastrarUsuario(UsuarioModel usuario) { 	 
		ModelAndView mv = new ModelAndView("/administracao/cadastrar_usuario");
		
        return mv;  	
    	    	
    }
	
	
	
	
	
	public String getRandomPass() {
		char[] chart ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		char[] senha= new char[8];

		int chartLenght = chart.length;
		Random rdm = new Random();

		for (int x=0; x<8; x++)
		senha[x] = chart[rdm.nextInt(chartLenght)];

		return new String(senha);
	}


}
