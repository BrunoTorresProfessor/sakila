package br.com.sakila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sakila.model.PermissaoModel;



public interface PermissaoRepository extends JpaRepository<PermissaoModel, Long> {
	
	  //retorna o siape através do login de rede
	  @Query(value ="select p.id_permissao "
	  		+ "from sakila.permissoes p "
	  		+ "where p.nome = ?1 "
	  		+ "limit 1", nativeQuery = true)
	  Long retornaIdPermissao(String nome_permissao);
	
}
