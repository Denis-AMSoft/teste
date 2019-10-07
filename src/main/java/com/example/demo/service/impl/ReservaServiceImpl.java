package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.model.enums.StatusCadastro;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.NegocioException;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public List<Reserva> todos() {
		return reservaRepository.findAll();
	}

	@Override
	public void remover(Reserva reserva) {
		reservaRepository.delete(reserva);
	}

	@Override
	@Transactional
	public Reserva salvar(Reserva reserva) {
		Usuario usuario = usuarioService.porId(reserva.getUsuario().getCodigo());
		verificaUsuarioAtivo(usuario);
		verificaHorarioReserva(reserva);
		reserva.setUsuario(usuario);
		return reservaRepository.saveAndFlush(reserva);
	}

	private void verificaHorarioReserva(Reserva reserva) {
		List<Reserva> reservas = todos();
		for (Reserva reserva2 : reservas) {
			Date inicio = new Date(reserva2.getDataInicial().getTime());
			Date fim = new Date(reserva2.getDataFinal().getTime());

			if (reserva.getDataFinal().before(fim) && reserva.getDataInicial().after(inicio)) {
				throw new NegocioException("Ja Existe Reserva neste peiodo");
			}
		}
	}

	private void verificaUsuarioAtivo(Usuario usuario) {
		if (usuario.getStatusUsuario() != StatusCadastro.ATIVO) {
			throw new NegocioException("Usuario nao Ativo, procuro o Administrador do sistema !!!");
		}
	}

	@Override
	public Reserva porId(Long id) {
		return reservaRepository.findById(id).orElse(new Reserva());
	}

}
