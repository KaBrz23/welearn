package com.welearn.welearn.usuario;

import com.welearn.welearn.usuario.dto.UsuarioProfileResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario create(Usuario user){
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return repository.save(user);
    }

    public UsuarioProfileResponse getUserProfile(String email) {
        return repository.findByEmail(email)
                .map(UsuarioProfileResponse::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Usuario getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }

    public Usuario update(Long id, Usuario updatedUser) {
        Usuario existingUser = getById(id);
        existingUser.setNome(updatedUser.getNome());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setCpf(updatedUser.getCpf());
        existingUser.setData_nascimento(updatedUser.getData_nascimento());

        if (updatedUser.getSenha() != null && !updatedUser.getSenha().isEmpty()) {
            existingUser.setSenha(passwordEncoder.encode(updatedUser.getSenha()));
        }

        return repository.save(existingUser);
    }

    public void delete(Long id) {
        Usuario user = getById(id);
        repository.delete(user);
    }

}