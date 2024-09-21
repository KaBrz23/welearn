package com.welearn.welearn.categoria;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria create(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));
    }

    public Categoria update(Long id, Categoria updatedCategoria) {
        Categoria existingCategoria = getById(id);
        existingCategoria.setNome(updatedCategoria.getNome());
        existingCategoria.setDescricao(updatedCategoria.getDescricao());
        return repository.save(existingCategoria);
    }

    public void delete(Long id) {
        Categoria categoria = getById(id);
        repository.delete(categoria);
    }
}
