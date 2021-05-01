package com.vivere.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivere.crud.entity.Usuario;
import com.vivere.crud.repository.UsuarioRepository;

@RestController
public class LoginController {
	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	ResponseEntity<String> logar(@RequestParam(value = "login") String login,
			@RequestParam(value = "senha") String senha) {
		Usuario autentica = usuarioRepository.findByLoginAndSenha(login, senha);

		if (autentica == null) {
			return ResponseEntity.ok("Usuario não encontrado!");

		} else if (autentica.getStatus().equals("A")) {
			return ResponseEntity.ok("Usuario ativo!");

		} else {

			return ResponseEntity.ok("Usuario cancelado");

		}

	}

}
	
