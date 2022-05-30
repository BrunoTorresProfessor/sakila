package br.com.sakila.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "grupos" , schema = "sakila")
public class GrupoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo")
	private Long idGrupo;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToMany
	@JoinTable(name="usuarios_grupos",
    joinColumns={@JoinColumn(name="grupos_id_grupo")},
    inverseJoinColumns={@JoinColumn(name="usuarios_id_usuario")})
	private Set<UsuarioModel> usuarios = new HashSet<UsuarioModel>();
	
	@ManyToMany
	@JoinTable(name="grupos_permissoes",
    joinColumns={@JoinColumn(name="grupos_id_grupo")},
    inverseJoinColumns={@JoinColumn(name="permissoes_id_permissao")})
	private List<PermissaoModel> permissoes;


	public Set<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<PermissaoModel> getPermissoes() {
		return permissoes;
	}
	
	public void setPermissoes(List<PermissaoModel> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoModel other = (GrupoModel) obj;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + idGrupo + ", nome=" + nome + "]";
	}
}
