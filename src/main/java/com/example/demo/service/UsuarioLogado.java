package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

@Service
public interface UsuarioLogado {

	/**
	 * Retorna o usuário que se encontra na sessão.
	 * @return {@link Usuario}
	 */
	Usuario getUsuario();
}
