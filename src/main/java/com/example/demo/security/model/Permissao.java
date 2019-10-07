package com.example.demo.security.model;

public enum Permissao {

	ADMIN("Administrador"),
	USER("Usuário Padrão"),
	SINDICO("Sindico");	
	
	private String descricao;
	
	private Permissao(String descricao) {
		this.descricao = descricao;
	}	
	
	private Permissao() {
	
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
		
}
