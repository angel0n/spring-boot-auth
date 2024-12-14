package com.auth.auth.controller;

import com.auth.auth.dto.LoginDto;
import com.auth.auth.dto.RegisterDto;
import com.auth.auth.dto.ResponseLoginDto;
import com.auth.auth.entity.Usuario;
import com.auth.auth.repositories.UsuarioRepository;
import com.auth.auth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Authcontroller {
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto body){
        Usuario user = this.userRepository.findByUsuarioEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        if(passwordEncoder.matches(body.password(), user.getUsuarioSenha())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseLoginDto(token, user.getUsuarioNome()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto body){
        Usuario user = new Usuario();
        user.setUsuarioSenha(this.passwordEncoder.encode(body.password()));
        user.setUsuarioNome(body.nome());
        user.setUsuarioEmail(body.email());

        this.userRepository.save(user);

        String token = this.tokenService.generateToken(user);

        return ResponseEntity.ok(new ResponseLoginDto(token, user.getUsuarioNome()));

    }
}
