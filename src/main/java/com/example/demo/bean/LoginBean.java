package com.example.demo.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired(required = false)
	private FacesContext facesContext;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuario;
	private String email;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}
	}
	
	public void login() {
		try {
			usuario = usuarioService.porEmail(email);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.xhtml");
			dispatcher.forward(request, response);

			facesContext.responseComplete();
			if ("true".equals(request.getParameter("invalid"))) {
				FacesUtil.addErrorMessage("Usuário ou senha inválido!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}