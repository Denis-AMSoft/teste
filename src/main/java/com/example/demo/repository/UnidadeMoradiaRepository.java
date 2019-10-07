package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UnidadeMoradia;
import com.example.demo.model.Usuario;

@Repository
public interface UnidadeMoradiaRepository extends JpaRepository<UnidadeMoradia, Long> {

	UnidadeMoradia findByUsuario(Usuario usuario);




}
