package br.com.sakila.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sakila.model.PermissaoModel;
import br.com.sakila.model.UsuarioModel;



@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	
	  boolean existsByCpf(String cpf);
	  boolean existsByEmail(String email);
	  UsuarioModel getOneByCpf(String cpf);
	  List<UsuarioModel> findAllByPermissoesIn(List<PermissaoModel> permissoes, Sort nome);
	 
	  @Modifying
	  @Query(value ="update sakila.usuarios "
	  		+ "set senha = ?1 "
	  		+ "where cpf=?2 ", nativeQuery = true)
	  @Transactional
	  void alterarSenha(String novaSenha, String cpf);
	  

	  


	
}


