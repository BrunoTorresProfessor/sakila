package br.com.sakila.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sakila.model.GrupoModel;




@Repository
public interface GrupoRepository extends JpaRepository<GrupoModel, Long>{
	
	//List<GrupoModel> findByUsuariosIn(UsuarioModel usuario);
	
}
