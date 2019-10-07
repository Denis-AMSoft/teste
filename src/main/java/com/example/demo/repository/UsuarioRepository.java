package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("FROM Usuario u WHERE u.statusUsuario = 'CADASTRO' ")
	List<Usuario> findAllStatusUsuario();

	@EntityGraph(attributePaths="permissoes")
	Usuario findByUsername(String username);

	Usuario findByEmail(String email);

	Usuario findBySindicoTrue();
	
}
