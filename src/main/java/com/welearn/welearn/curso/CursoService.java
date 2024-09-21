package com.welearn.welearn.curso;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public List<Curso> findAll() {
        return repository.findAll();
    }

    public Curso create(Curso curso) {
        return repository.save(curso);
    }

    public Curso getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + id));
    }

    public Curso update(Long id, Curso updatedCurso) {
        Curso existingCurso = getById(id);
        existingCurso.setNome(updatedCurso.getNome());
        existingCurso.setDescricao(updatedCurso.getDescricao());
        existingCurso.setCategoria(updatedCurso.getCategoria());
        existingCurso.setInstrutor(updatedCurso.getInstrutor());

        return repository.save(existingCurso);
    }

    public void delete(Long id) {
        Curso curso = getById(id);
        repository.delete(curso);
    }
}
