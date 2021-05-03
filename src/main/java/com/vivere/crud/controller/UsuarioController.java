package com.vivere.crud.controller;

import java.io.FileNotFoundException;
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

import com.vivere.crud.entity.Usuario;
import com.vivere.crud.repository.UsuarioRepository;
import com.vivere.crud.service.JasperUsuario;

import net.sf.jasperreports.engine.JRException;

@RestController
public class UsuarioController {

	
	@Autowired
	JasperUsuario jasperUsuario;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuario")
	List<Usuario> read() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuario/{id}")
	Optional<Usuario> read1(@PathVariable Integer id) {
		return usuarioRepository.findById(id);
	}

	@GetMapping("/usuario/filtra") // Só mudei name por nome e deu certo
	List<Usuario> findByNomeAndEmail(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "email", required = false) String email) {
		if (nome != null && email != null)
			return usuarioRepository.findByNomeAndEmail(nome, email);
		else if (nome != null)
			return usuarioRepository.findByNome(nome);
		else if (email != null)
			return usuarioRepository.findByEmail(email);
		else
			return usuarioRepository.findAll();

	}

	@PostMapping("/usuario")
	public ResponseEntity<Void> criarUsuario(@RequestBody Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(usuario.getId());
		return ResponseEntity.created(uri).build();

	}

	@PutMapping("/usuario") //optei por atualizar apenas pelo corpo da msg
	ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		if (usuarioRepository.findById(usuario.getId()).isPresent())
			return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.OK);
		else
			return new ResponseEntity<Usuario>(usuario, HttpStatus.BAD_REQUEST);
	}
		
		@DeleteMapping("/usuario/{id}")
		public String delete(@PathVariable Integer id) {
			//return ResponseEntity<Void>(usuarioRepository.deleteById(id), HttpStatus.NO_CONTENT);
			usuarioRepository.deleteById(id);
			return "Usuario deletado com sucesso!";
	}
		
		
		@GetMapping(value="/report/{format}")
		public String generateReport (@PathVariable String format) throws FileNotFoundException, JRException{
		return jasperUsuario.exportReport(format);
		}

}
