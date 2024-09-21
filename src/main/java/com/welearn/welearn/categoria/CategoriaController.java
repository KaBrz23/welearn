package com.welearn.welearn.categoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/categoria")
@CacheConfig(cacheNames = "categorias")
@Slf4j
@Tag(name = "Categoria", description = "Gerenciamento categoria dos cursos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Cacheable
    @Operation(summary = "Listar todas as categorias")
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Listar Categoria por id")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return ResponseEntity.of(Optional.ofNullable(categoriaService.getById(id)));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Cadastrar Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400")
    })
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        Categoria createdCategoria = categoriaService.create(categoria);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCategoria.getId_categoria())
                .toUri();

        return ResponseEntity.created(uri).body(createdCategoria);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deletar Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "401")
    })
    public void delete(@PathVariable Long id) {
        log.info("Apagando categoria com ID {}", id);
        categoriaService.delete(id);
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualizar Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "404")
    })
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria updatedCategoria) {
        log.info("Atualizando categoria com id {} para {}", id, updatedCategoria);
        Categoria categoria = categoriaService.update(id, updatedCategoria);
        return ResponseEntity.ok(categoria);
    }
}
