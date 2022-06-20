package br.com.sakila.security;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sakila.model.ePermissao;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
	       authorizeRequests()
	           .antMatchers("/webjars/**").permitAll() //configuração para permitir que o bootstrap funcione na aplicação
	           .antMatchers("/images/**").permitAll() //configuração para permitir que o bootstrap funcione na aplicação
	           .antMatchers("/login").permitAll() //libera o acesso a página para todos os usuários
	           .antMatchers("/atores/atores").hasAnyRole(ePermissao.ATOR.toString()) //libera o acesso a página para um perfil específico
	           .antMatchers("/atores/enderecos").hasAnyRole(ePermissao.ADMIN.toString()) //libera o acesso a página para um perfil específico
	           .antMatchers("/cadastro/recuperar_senha").permitAll() 
	           .anyRequest()
	           .authenticated()
	           .and()
	       .formLogin()
	       	   .loginPage("/login") //indica uma pagina de login diferente da página padrão	         
	           .usernameParameter("username") //indica o parametro do usuario
               .passwordParameter("password") //indica o parametro da senha
	           .successForwardUrl("/postlogin") //indica pra qual método o sistema é direcionado após o login	           
	           .permitAll()
	           .and()
	       .logout()	       
	       	   .logoutSuccessUrl("/login?logout") //indica a página de logout
	           .permitAll()
	           .and()
	       .rememberMe();  
	   } 
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
		//Serve de exemplo para gerar um senha criptografada
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("123456"));
		//Cripitografa a senha para salvar no banco de dados
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	 
	    
	  
	}
}




