package com.example.demo.bean.evento;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Reserva;
import com.example.demo.security.Seguranca;
import com.example.demo.service.EmailService;
import com.example.demo.service.ReservaService;
import com.example.demo.util.FacesUtil;

@Named
@ViewScoped
public class CadastroEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ReservaService service;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private Seguranca seguranca;
	
	private Date hoje;

	private Reserva reserva;

	@PostConstruct
	public void inicializar() {
		setHoje(new Date());

		limpar();
		FacesContext fContext = FacesContext.getCurrentInstance();
		if (fContext != null) {
			HttpServletRequest request = (HttpServletRequest) fContext.getExternalContext().getRequest();
			if (request.getParameter("reserva") != null) {
				String url = request.getParameter("reserva");
				Long id = Long.valueOf(url);
				reserva = service.porId(id);
				request.removeAttribute("usuario");
			}
		}
	}

	public void novoEvento() {
		try {
			reserva = service.salvar(reserva);
			emailService.enviar(reserva);
			emailService.enviarNovoEvento(reserva);
			FacesUtil.addInfoMessage("Evento " + reserva.getCodigo() + "  salvo !!");
			FacesUtil.addInfoMessage("Unidade N°:  " + reserva.getUsuario().getMoradia().getUnidade() + "  salvo !!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("ERRO :" + e.getMessage());
		}
	}

	private void limpar() {
		reserva = new Reserva();
		if (FacesContext.getCurrentInstance() != null) {
			reserva.setUsuario(seguranca.getUsuarioLogado().getUsuario());
		}
	}

	public boolean isEditando() {
		return reserva != null;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Date getHoje() {
		return hoje;
	}

	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}

}
