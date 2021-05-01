package com.vivere.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivere.crud.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByNomeAndEmail(String nome, String email);

	List<Usuario> findByNome(String nome);

	List<Usuario> findByEmail(String email);

	Usuario findByLoginAndSenha(String login, String senha);

}
