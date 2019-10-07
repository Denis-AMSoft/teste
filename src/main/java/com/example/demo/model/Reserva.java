package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.demo.model.enums.StatusReserva;


@Entity
@Table(name = "reserva")
public class Reserva  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codigo;
 
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}  )
	private Usuario usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicial;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;
	
	@Length(max = 1024)
	private String descricao;

	@Length(max = 16388)
	private String assinatura;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_reserva", nullable = false)
	private StatusReserva statusReserva = StatusReserva.CADASTRO;
	
	
	public Reserva(Long codigo, Usuario usuario, Date dataInicial, Date dataFinal, String descricao, String assinatura,
			@NotNull StatusReserva statusReserva) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.descricao = descricao;
		this.assinatura = assinatura;
		this.statusReserva = statusReserva;
	}

	public Reserva() {
		
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

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

	public StatusReserva getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(StatusReserva statusReserva) {
		this.statusReserva = statusReserva;
	}
	
}
