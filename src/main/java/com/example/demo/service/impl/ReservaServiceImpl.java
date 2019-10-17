package com.example.demo.service.impl;

import java.util.ArrayList;
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
	public void salvar(Reserva reserva) {

		Usuario usuario = usuarioService.porId(reserva.getUsuario().getCodigo());
		verificaUsuarioAtivo(usuario);
		if (reserva.getCodigo() == null) {
			verificaHorarioReserva(reserva);
		}
		
		
		reserva.setUsuario(usuario);
		reservaRepository.saveAndFlush(reserva);

	}

	private void verificaHorarioReserva(Reserva reserva) {

		Long iniRESERVA = reserva.getDataInicial().getTime();
		Long fimRESERVA = reserva.getDataFinal().getTime();

		List<Reserva> reservas = reservaPorDia(reserva.getDataInicial(), reserva.getDataFinal());

		for (Reserva reserva2 : reservas) {

			Long iniRESERVA2 = reserva2.getDataInicial().getTime();
			Long fimRESERVA2 = reserva2.getDataFinal().getTime();

			if (iniRESERVA >= iniRESERVA2 && iniRESERVA <= fimRESERVA2) {
				throw new NegocioException("Ja Existe Reserva neste peiodo");
			}
			if (fimRESERVA >= iniRESERVA2 && fimRESERVA <= fimRESERVA2) {
				throw new NegocioException("Ja Existe Reserva neste peiodo");
			}

		}
	}

	private List<Reserva> reservaPorDia(Date dataInicial, Date dataFinal) {
		List<Reserva> listaDia = new ArrayList<Reserva>();

		listaDia.addAll(reservaRepository.findByDataInicialBetween(dataInicial, dataFinal));
		listaDia.addAll(reservaRepository.findByDataFinalBetween(dataInicial, dataFinal));

		return listaDia;
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
