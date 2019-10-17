package com.example.demo.bean.evento;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Reserva;
import com.example.demo.model.enums.StatusReserva;
import com.example.demo.service.EmailService;
import com.example.demo.service.ReservaService;
import com.example.demo.util.FacesUtil;

@Named
@ViewScoped
public class ListaEventosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ReservaService service;
	
	@Autowired
	private EmailService emailService;
	
	private List<Reserva> todosEventos;

	@PostConstruct
	public void inicializar() {
		consultar();
	}

	public void excluir(Reserva reserva) {
		if (reserva.getStatusReserva() == StatusReserva.CONCLUIDO) {
			service.remover(reserva);
			FacesUtil.addInfoMessage("Reserva excluído com sucesso!");
			consultar();
		} else {
			FacesUtil.addErrorMessage("O Status da reserva não permite a exclução!");
		}
	}
	
	public void salvarEvento(Reserva reserva) {
		try {	
			service.salvar(reserva);
			emailService.enviar(reserva);
			FacesUtil.addInfoMessage("Reserva Atualizada com sucesso!!!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(e.getMessage());
		}
	}
	
	public StatusReserva[] getStatusReserva() {
		return StatusReserva.values();
	}

	public void consultar() {
		setTodosEventos(service.todos());
	}

	public List<Reserva> getTodosEventos() {
		return todosEventos;
	}

	public void setTodosEventos(List<Reserva> todosEventos) {
		this.todosEventos = todosEventos;
	}

}
