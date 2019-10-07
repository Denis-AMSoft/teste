package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

@Service
public interface UsuarioService {

	List<Usuario> todos();

	Usuario salvar(Usuario usuario);

	Usuario porId(Long id);

	void delete(Usuario usuario);

	Usuario porEmail(String email);

	Usuario getSindico();

	void redefinirSenha(Usuario usuario);

}
