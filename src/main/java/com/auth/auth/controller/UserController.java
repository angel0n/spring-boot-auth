package com.auth.auth.controller;

import com.auth.auth.entity.Usuario;
import com.auth.auth.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UsuarioRepository usuarioRepository;

    @GetMapping("/getUser")
    public List<Usuario> getUser(){
        return usuarioRepository.findAll();
    }
}
