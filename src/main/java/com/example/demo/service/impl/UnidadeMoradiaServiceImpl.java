package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UnidadeMoradiaRepository;
import com.example.demo.service.UnidadeMoradiaService;

@Service
public class UnidadeMoradiaServiceImpl implements UnidadeMoradiaService {

	@Autowired
	private UnidadeMoradiaRepository repository;

	@Override
	public List<UnidadeMoradia> todos() {
		return repository.findAll();
	}

	@Override
	public UnidadeMoradia porUsurio(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

	@Override
	@Transactional
	public UnidadeMoradia salvar(UnidadeMoradia moradia) {
		return repository.saveAndFlush(moradia);
	}

	@Override
	public UnidadeMoradia porId(Long id) {
		return repository.findById(id).orElse(null);
	}

}
