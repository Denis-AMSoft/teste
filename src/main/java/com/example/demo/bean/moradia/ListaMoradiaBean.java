package com.example.demo.bean.moradia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.enums.StatusCadastro;
import com.example.demo.service.UnidadeMoradiaService;
import com.example.demo.util.FacesUtil;

@Named
@ViewScoped
public class ListaMoradiaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UnidadeMoradiaService service;

	List<UnidadeMoradia> listUnidadeMoradia = new ArrayList<UnidadeMoradia>();
	
	@PostConstruct
	public void inicializar() {
		consultar();
	}
		
	public void excluir(UnidadeMoradia moradia) {
		moradia.setStatusUnidadeMoradia(StatusCadastro.EXCUIDO);
		moradia = service.salvar(moradia);
		FacesUtil.addInfoMessage("Cliente excluído com sucesso!");
		consultar();
	}
	
	public void consultar() {
		listUnidadeMoradia = service.todos();
	}

	public List<UnidadeMoradia> getListUnidadeMoradia() {
		return listUnidadeMoradia;
	}

	public void setListUnidadeMoradia(List<UnidadeMoradia> listUnidadeMoradia) {
		this.listUnidadeMoradia = listUnidadeMoradia;
	}

}
