package com.welearn.welearn.auth;

import com.welearn.welearn.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UsuarioRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){

        var user = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new RuntimeException("Access Denied"));

        if ( !passwordEncoder.matches(credentials.senha(), user.getSenha()) )
            throw new RuntimeException("Access Denied");

        return tokenService.create(user);
    }

}
