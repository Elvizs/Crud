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
import com.vivere.crud.entity.Livro;
import com.vivere.crud.repository.ClienteRepository;
import com.vivere.crud.repository.LivroRepository;

@RestController
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/livro/{id}")
	Optional<Livro> read(@PathVariable Integer id) {
		return livroRepository.findById(id);
	}

	@GetMapping("/livro")
	List<Livro> read() {
		return livroRepository.findAll();
	}

	@GetMapping("/livro/cliente")
	List<Livro> findByClienteId(@RequestParam(value = "cliente") Integer cliente) {
		if (cliente != null)
			return livroRepository.findByClienteId(cliente);

		else
			return livroRepository.findAll();
	}

	@PostMapping("/livro")
	public ResponseEntity<Void> create(@RequestBody Livro livro) {
		Cliente cli = clienteRepository.findById(livro.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		livro.setCliente(cli);
		livro = livroRepository.save(livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(livro.getId());
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("/livro")
	ResponseEntity<Livro> update(@RequestBody Livro livro) {
		if (livroRepository.findById(livro.getId()).isPresent())
			return new ResponseEntity<Livro>(livroRepository.save(livro), HttpStatus.OK);
		else
			return new ResponseEntity<Livro>(livro, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/livro/{id}")
	void delete(@PathVariable Integer id) {
		livroRepository.deleteById(id);
	}

}
