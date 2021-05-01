package com.vivere.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivere.crud.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeAndCpfCnpjAndCidadeAndUf(String nome, String cpfCnpj, String cidade, String uf);

	List<Cliente> findByNome(String nome);

	List<Cliente> findByCpfCnpj(String cpfCnpj);

	List<Cliente> findByCidade(String cidade);

	List<Cliente> findByUf(String uf);

}
