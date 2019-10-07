package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Reserva;
import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.Usuario;

@Service
public interface EmailService {
	
	void enviar( Reserva reserva);

	void enviarNovoUsuario(Usuario usuario);

	void enviarNovoEvento(Reserva reserva);

	void enviarMoradia(UnidadeMoradia moradia);

	void enviarSenhaNova(Usuario usuario, String senha);

}
