package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.model.enums.StatusCadastro;

@Entity
@Table(name = "unidade_moradia")
public class UnidadeMoradia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codigo;

	private Integer unidade;

	@OneToOne(cascade = CascadeType.ALL ,fetch =  FetchType.EAGER)
	private Usuario usuario;
	

	@Enumerated(EnumType.STRING)
	@Column(name = "status_unidade_moradia", nullable = false)
	private StatusCadastro statusUnidadeMoradia = StatusCadastro.PEDENTE;

	public StatusCadastro getStatusUnidadeMoradia() {
		return statusUnidadeMoradia;
	}

	public void setStatusUnidadeMoradia(StatusCadastro statusUnidadeMoradia) {
		this.statusUnidadeMoradia = statusUnidadeMoradia;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

}
