package com.example.demo.model.enums;

public enum TipoUsuario {
	
	SINDICO("Sindico"),
	MORADOR( "Morador");
		
	private String descricao;
	
	TipoUsuario(String descricao) {
		this.descricao =  descricao;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
}
