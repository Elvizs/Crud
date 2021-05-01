package com.vivere.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivere.crud.entity.Cliente;
import com.vivere.crud.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping("/cliente/{id}")
	Optional<Cliente> busca(@PathVariable Integer id) {
		return clienteRepository.findById(id);
	}

	@GetMapping("/cliente")
	List<Cliente> read() {
		return clienteRepository.findAll();
	}

	@GetMapping("/cliente/busca") // Só mudei name por nome e deu certo
	List<Cliente> findByNomeAndEmail(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cpfCnpj", required = false) String cpfCnpj,
			@RequestParam(value = "cidade", required = false) String cidade,
			@RequestParam(value = "uf", required = false) String uf) {
		if (nome != null && cpfCnpj != null && cidade != null && uf != null)
			return clienteRepository.findByNomeAndCpfCnpjAndCidadeAndUf(nome, cpfCnpj, cidade, uf);
		else if (nome != null)
			return clienteRepository.findByNome(nome);
		else if (cpfCnpj != null)
			return clienteRepository.findByCpfCnpj(cpfCnpj);// colocar dois argunentos
		else if (cidade != null)
			return clienteRepository.findByCidade(cidade);
		else if (uf != null)
			return clienteRepository.findByUf(uf);
		else
			return clienteRepository.findAll();

	}

	@PutMapping("/cliente") // optei por atualizar apenas pelo corpo da msg
	ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
		if (clienteRepository.findById(cliente.getId()).isPresent())
			return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.OK);
		else
			return new ResponseEntity<Cliente>(cliente, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/cliente")
	public ResponseEntity<Void> create(@RequestBody Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(cliente.getId());
		return ResponseEntity.created(uri).build();

	}

	@DeleteMapping("/cliente/{id}")
	void delete(@PathVariable Integer id) {
		clienteRepository.deleteById(id);
	}

}
