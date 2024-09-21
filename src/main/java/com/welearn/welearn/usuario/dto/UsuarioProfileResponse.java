package com.welearn.welearn.usuario.dto;

import com.welearn.welearn.usuario.Usuario;

public record UsuarioProfileResponse(
        String name,
        String email
) {
    public UsuarioProfileResponse(Usuario user){
        this(user.getNome(), user.getEmail());
    }
}
