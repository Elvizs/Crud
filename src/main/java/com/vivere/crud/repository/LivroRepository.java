package com.vivere.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivere.crud.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

	List<Livro> findByClienteId(Integer cliente);

}
