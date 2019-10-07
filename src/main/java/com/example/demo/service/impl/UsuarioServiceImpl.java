package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.model.enums.StatusCadastro;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.model.Permissao;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public List<Usuario> todos() {
		return usuarioRepositorio.findAll();
	}

	@Override
	@Transactional
	public Usuario salvar(Usuario usuario) {
		if (usuario.getCodigo() == null) {
			usuario.getMoradia().setStatusUnidadeMoradia(StatusCadastro.PEDENTE);
		}
		usuario.setPermissao(getPermissao(usuario));
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return usuarioRepositorio.saveAndFlush(usuario);
	}

	private Permissao getPermissao(Usuario usuario) {
		if (usuario.isSindico()) {
			return Permissao.SINDICO;
		} else {
			return Permissao.USER;
		}
	}

	@Override
	public Usuario porId(Long id) {
		return usuarioRepositorio.findById(id).orElse(new Usuario());
	}

	@Override
	public void delete(Usuario usuario) {
		usuarioRepositorio.delete(usuario);
	}

	@Override
	public Usuario porEmail(String email) {
		return usuarioRepositorio.findByEmail(email);
	}

	@Override
	public Usuario getSindico() {
		return usuarioRepositorio.findBySindicoTrue();
	}

	@Override
	@Transactional
	public void redefinirSenha(Usuario usuario) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuarioRepositorio.saveAndFlush(usuario);	
	}

}
