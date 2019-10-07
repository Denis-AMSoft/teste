package com.example.demo.model.enums;

public  enum StatusCadastro {
	
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	PEDENTE("Pedente"),
	CADASTRO("Cadastro"), 
	EXCUIDO("Excluido");
	
	private String descricao;

	StatusCadastro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
