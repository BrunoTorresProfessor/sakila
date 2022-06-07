package br.com.sakila.service;

import br.com.sakila.model.UsuarioModel;

public interface UsuarioService {
	
	String CadastrarUsuario(UsuarioModel usuario,String confirmarEmail,Long idUsuario);

}
