package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.example.demo.model.enums.StatusCadastro;
import com.example.demo.model.enums.TipoUsuario;
import com.example.demo.security.model.Permissao;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codigo;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@NotEmpty
	private String username;

	@CPF
	private String cpf;

	@Email
	private String email;

	private String nome;

	private String password;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UnidadeMoradia moradia;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reserva> reservas;

	private boolean sindico;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_usuario", nullable = false)
	private StatusCadastro statusUsuario = StatusCadastro.CADASTRO;

	@Enumerated(EnumType.STRING)
	@Column(name = "permissao")
	private Permissao permissao;

	public Usuario(Long codigo, TipoUsuario tipoUsuario, @NotEmpty String username, @CPF String cpf,
			@Email String email, String password, @NotNull StatusCadastro statusUsuario) {
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.username = username;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.statusUsuario = statusUsuario;
	}

	public Usuario() {

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusCadastro getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(StatusCadastro statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UnidadeMoradia getMoradia() {
		return moradia;
	}

	public void setMoradia(UnidadeMoradia moradia) {
		this.moradia = moradia;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public boolean isSindico() {
		return sindico;
	}

	public void setSindico(boolean sindico) {
		this.sindico = sindico;
	}

}
