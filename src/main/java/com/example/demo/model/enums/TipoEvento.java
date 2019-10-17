package com.example.demo.model.enums;

public enum TipoEvento {
		
	HAPPYHOUR("Happy hour"),
	ALMOCO("almoço"),
	JANTAR("jantar"),
	CAFEDAMANHA("café da manhã"),
	ANIVERSARIO("festa de aniversário"),
	OUTRO("outro");
	
	private String descricao;
	
	TipoEvento(String descricao) {
		this.descricao =  descricao;
	}
	
	
	public String getDescricao() {
		return descricao;
	}

}
