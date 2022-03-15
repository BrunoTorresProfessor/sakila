package br.com.sakila.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language",schema = "sakila")

public class LanguageModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "language_id")
	private Integer languageId;
	@Column(name = "name")
	private String name;
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 

}
