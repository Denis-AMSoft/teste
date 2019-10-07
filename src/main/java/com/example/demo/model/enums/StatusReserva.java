package com.example.demo.model.enums;

public enum StatusReserva {
	
	RESERVADO("Reservado"),
	PEDENTE("Pedente"),
	CADASTRO("Cadastro"), 
	CONCLUIDO("Concluido"),
	EXCUIDO("Excluido");
	
	private String descricao;

	StatusReserva(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
