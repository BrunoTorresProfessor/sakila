package br.com.sakila.model;

public enum ePermissao {
	ATOR("ROLE_ATOR"),
	ADMIN("ROLE_ADMIN");
 
	public final String valor;
    ePermissao(String valorOpcao){
        valor = valorOpcao;
    }
    public String getValor(){
        return valor;
    }
}
