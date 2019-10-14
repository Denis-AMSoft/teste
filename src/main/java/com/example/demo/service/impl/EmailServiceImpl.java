package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.model.Mensagem;
import com.example.demo.model.Reserva;
import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.Usuario;
import com.example.demo.service.EmailService;
import com.example.demo.service.UsuarioService;


@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	public JavaMailSender emailSender;
	
	/**
	 *envia Moradia
	 */
	@Override
	@Async
	public void enviarMoradia(UnidadeMoradia moradia) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		String corpo = "Ola " + moradia.getUsuario().getNome() + 
				"\n Sua Unidade de Moradia : " + moradia.getUnidade() + 
				"\n O Status :" + moradia.getStatusUnidadeMoradia().getDescricao();

		List<String> emails = new ArrayList<String>();
		emails.add(moradia.getUsuario().getEmail());
		Mensagem mensagem = new Mensagem(emails, "Status do Cadastro da Moradia", corpo);
		simpleMailMessage.setFrom(mensagem.getRemetente());
		simpleMailMessage.setTo(mensagem.getDestinatarios().toArray(new String[mensagem.getDestinatarios().size()]));
		simpleMailMessage.setSubject(mensagem.getAssunto());
		simpleMailMessage.setText(mensagem.getCorpo());

		emailSender.send(simpleMailMessage);
		
	}

	/**
	 *Envia para o Sindico
	 */
	@Override
	@Async
	public void enviarNovoEvento(Reserva reserva) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		String dataInicial = dt.format(reserva.getDataInicial());

		String dataFinal = dt.format(reserva.getDataFinal());

		String corpo = "Usuario : " + reserva.getUsuario().getNome() +" , fez uma nova reserva."+
				"\n Reserva para seguinte data : " + dataInicial+ " a " + dataFinal +
				"\n O Status :" + reserva.getStatusReserva().getDescricao()+
				"\n Consulte o Sistema por o quanto antes.";

		Usuario sindico = usuarioService.getSindico();

		List<String> emails = new ArrayList<String>();
		emails.add(sindico.getEmail());
		Mensagem mensagem = new Mensagem(emails, "Cadastro de Nova Reserva : " + reserva.getUsuario().getNome(), corpo);
		simpleMailMessage.setFrom(mensagem.getRemetente());
		simpleMailMessage.setTo(mensagem.getDestinatarios().toArray(new String[mensagem.getDestinatarios().size()]));
		simpleMailMessage.setSubject(mensagem.getAssunto());
		simpleMailMessage.setText(mensagem.getCorpo());

		emailSender.send(simpleMailMessage);
	}

	/**
	 *Envia para Usuario
	 */
	@Async
	public void enviar(Reserva reserva) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		String dataInicial = dt.format(reserva.getDataInicial());

		String dataFinal = dt.format(reserva.getDataFinal());

		String corpo = "Ola " + reserva.getUsuario().getNome() + 
				"\n Sua Reserva para seguinte data : " + dataInicial+ " a " + dataFinal + 
				"\n O Status :" + reserva.getStatusReserva().getDescricao();

		List<String> emails = new ArrayList<String>();
		emails.add(reserva.getUsuario().getEmail());
		Mensagem mensagem = new Mensagem(emails, "Status de sua reserva", corpo);
		simpleMailMessage.setFrom(mensagem.getRemetente());
		simpleMailMessage.setTo(mensagem.getDestinatarios().toArray(new String[mensagem.getDestinatarios().size()]));
		simpleMailMessage.setSubject(mensagem.getAssunto());
		simpleMailMessage.setText(mensagem.getCorpo());

		emailSender.send(simpleMailMessage);
	}

	
	/**
	 *Envia para o Sindico
	 */
	@Override
	@Async
	public void enviarNovoUsuario(Usuario usuario) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		String corpo = "Existe usuario pendente de : " + usuario.getNome() + "\n Favor Ativar usuario o quanto antes"
				+ "\n O Status :" + usuario.getStatusUsuario().getDescricao();

		Usuario sindico = usuarioService.getSindico();

		List<String> emails = new ArrayList<String>();
		emails.add(sindico.getEmail());
		Mensagem mensagem = new Mensagem(emails, "Cadastro de Usuario Novo: " + usuario.getNome(), corpo);
		simpleMailMessage.setFrom(mensagem.getRemetente());
		simpleMailMessage.setTo(mensagem.getDestinatarios().toArray(new String[mensagem.getDestinatarios().size()]));
		simpleMailMessage.setSubject(mensagem.getAssunto());
		simpleMailMessage.setText(mensagem.getCorpo());

		emailSender.send(simpleMailMessage);

	}

	/**
	 *Envia nova senha de Usuario
	 */
	@Override
	@Async
	public void enviarSenhaNova(Usuario usuario, String senha) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		String corpo = "Caro usuario : " + usuario.getNome() + 
				"\n Usar a seguinte senha : "+senha+
				"\n Favor usuario troque sua senha o quanto antes."
				+ "\n O Status :" + usuario.getStatusUsuario().getDescricao();

		List<String> emails = new ArrayList<String>();
		emails.add(usuario.getEmail());
		Mensagem mensagem = new Mensagem(emails, "Redefinição de Senmha de Usuario : " + usuario.getNome(), corpo);
		simpleMailMessage.setFrom(mensagem.getRemetente());
		simpleMailMessage.setTo(mensagem.getDestinatarios().toArray(new String[mensagem.getDestinatarios().size()]));
		simpleMailMessage.setSubject(mensagem.getAssunto());
		simpleMailMessage.setText(mensagem.getCorpo());

		emailSender.send(simpleMailMessage);
		
	}

}
