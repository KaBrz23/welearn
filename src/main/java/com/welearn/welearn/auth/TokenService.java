package com.welearn.welearn.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.welearn.welearn.usuario.Usuario;
import com.welearn.welearn.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private final UsuarioRepository userRepository;
    private Algorithm algorithm;

    public TokenService(UsuarioRepository userRepository, @Value("${jwt.secret}") String secret) {
        this.userRepository = userRepository;
        algorithm = Algorithm.HMAC256(secret);
    }

    public Token create(Usuario user){
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
                .withIssuer("orderai")
                .withSubject(user.getEmail())
                .withClaim("role", "admin")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token);
    }

    public Usuario getUserFromToken(String token) {
        var email =JWT.require(algorithm)
                .withIssuer("orderai")
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
