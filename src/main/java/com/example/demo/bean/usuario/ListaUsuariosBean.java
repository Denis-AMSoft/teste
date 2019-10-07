package com.example.demo.bean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.util.FacesUtil;

@Named
@ViewScoped
public class ListaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioService service;

	private List<Usuario> todosusuarios = new ArrayList<Usuario>();

	@PostConstruct
	public void inicializar() {
		consultar();
	}

	public void consultar() {
		todosusuarios = service.todos();
	}

	public void excluir(Usuario usuario) {
		service.delete(usuario);
		FacesUtil.addInfoMessage("Cliente excluído com sucesso!");
		consultar();
	}

	public List<Usuario> getTodosusuarios() {
		return todosusuarios;
	}

	public void setTodosusuarios(List<Usuario> todosusuarios) {
		this.todosusuarios = todosusuarios;
	}

}
