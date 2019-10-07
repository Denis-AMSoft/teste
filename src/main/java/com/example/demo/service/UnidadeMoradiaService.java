package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.Usuario;

@Service
public interface UnidadeMoradiaService {

	List<UnidadeMoradia> todos();
	
	UnidadeMoradia porId(Long id);

	UnidadeMoradia porUsurio(Usuario usuario);

	UnidadeMoradia salvar(UnidadeMoradia moradia);
	
}
