package com.welearn.welearn.curso;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@RestController
@RequestMapping("/curso")
@CacheConfig(cacheNames = "cursos")
@Slf4j
@Tag(name = "pedido", description = "Gerenciamento de pedidos do usuário")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar todos os cursos")
    @ApiResponse(responseCode = "200", description = "Cursos listados com sucesso")
    public List<Curso> findAll() {
        return cursoService.findAll();
    }

    @GetMapping("{id}")
    @Cacheable
    @Operation(summary = "Listar curso por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(cursoService.getById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cadastrar novo curso")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        Curso createdCurso = cursoService.create(curso);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCurso.getId_curso())
                .toUri();

        return ResponseEntity.created(uri).body(createdCurso);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deletar curso por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public void delete(@PathVariable Long id) {
        log.info("Apagando curso com ID {}", id);
        cursoService.delete(id);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar curso por ID")
    @CacheEvict(allEntries = true)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso updatedCurso) {
        log.info("Atualizando curso com id {} para {}", id, updatedCurso);
        Curso curso = cursoService.update(id, updatedCurso);
        return ResponseEntity.ok(curso);
    }
}
