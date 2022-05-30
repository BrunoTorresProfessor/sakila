package br.com.sakila.controller;

import java.util.Arrays;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sakila.mail.Mailer;
import br.com.sakila.mail.Mensagem;
import br.com.sakila.model.UsuarioModel;
import br.com.sakila.repository.GrupoRepository;
import br.com.sakila.repository.UsuarioRepository;

@Controller
public class CadastroController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
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
	
	@PostMapping(value="/administracao/cadastrar_usuario")
    public ModelAndView cadastrarUsuario(
    		@Valid @ModelAttribute("usuarioModel") UsuarioModel usuario,
    		BindingResult result,//o bindingResult deve sempre vir apos o objeto que ele está validando
    		@RequestParam("confirmar_email") String confirmarEmail,   
    		@RequestParam(required = false, name =  "id_usuario")  Long idUsuario,
    		RedirectAttributes atributes) 
	{ 
		//Verifica se existe algum erro na validação do bean validation da entidade
		if (result.hasErrors()) {
			//se existit erro retorna o mesmo usuario para a view para que não seja necessário digitar todos os dados novamente
			return cadastrar(usuario);
		}
		
		//redirecina para o método getmapping
		ModelAndView mv = new ModelAndView("redirect:listar_usuarios");
		if (usuario != null) {
			usuario.setId_usuario(idUsuario);
		}
		//O método save salva uma informação nova ou atualiza se o ID estiver setado
		usuarioRepository.save(usuario);
		
		atributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
		
		return mv;
    	    	
    }
	@GetMapping(value="/cadastro/cadastrar")
    public ModelAndView cadastrar(UsuarioModel usuario) { 	 
		ModelAndView mv = new ModelAndView("cadastro/cadastrar");
        return mv;      	
    }
	
	@GetMapping("/administracao/deletar/{id}")
	public String delete(UsuarioModel usuario,@PathVariable("id") Long id, RedirectAttributes attr) {
		usuario = (UsuarioModel)this.usuarioRepository.getOne(id);
		this.usuarioRepository.delete(usuario); //Exclui o usuário
		attr.addFlashAttribute("mensagem", "Usuário excluído com sucesso.");
		attr.addFlashAttribute("tipo_mensagem", "alert alert-success");
		return "redirect:../listar_usuarios";
	}
	@GetMapping(value = "/administracao/alterar_usuario/{id}")
	public ModelAndView alterarUnidadeUsuario(UsuarioModel usuarioModel,ModelMap model, @PathVariable("id") Long idUsuario) {
		    //cria uma instância do usuário vazia	
		 	UsuarioModel usuario = new UsuarioModel();	
		 	//consulta o usuário com o id recuperado via GET e adiciona no objeto instanciado
		 	usuario = usuarioRepository.getOne(idUsuario); 
		 	//passa esse objeto instanciado chamando de usuarioModel para a página cadastrarúsuario.html
		    model.addAttribute("usuarioModel", usuarioRepository.findById(idUsuario));	
	  		return new ModelAndView("administracao/cadastrar_usuario", model);					
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
	
	 @GetMapping(value = "/usuarios/listar_grupos/{id}")
	 public ModelAndView listarEventos(ModelMap model, @PathVariable("id") Long id_usuario) {
			  				
		    model.addAttribute("usuario", usuarioRepository.findById(id_usuario));				
	    	//model.addAttribute("grupos_usuarios", grupoRepository.findByUsuariosIn(usuarioRepository.getOne(id_usuario)));
	  		model.addAttribute("grupos", grupoRepository.findAll());		
	  		model.addAttribute("id_usuario", id_usuario);
	  		return new ModelAndView("usuarios/listar_grupos", model);
					
	}



}
