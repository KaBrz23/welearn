package com.welearn.welearn.usuario;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario create(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public Usuario update(Long id, Usuario updatedUser) {
        Usuario existingUser = getById(id);
        existingUser.setNome(updatedUser.getNome());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setSenha(updatedUser.getSenha());
        existingUser.setCpf(updatedUser.getCpf());
        existingUser.setData_nascimento(updatedUser.getData_nascimento());

        return repository.save(existingUser);
    }

    public void delete(Long id) {
        Usuario user = getById(id);
        repository.delete(user);
    }

}