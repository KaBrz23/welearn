package com.welearn.welearn.categoria;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_categoria;

    @NotNull
    @NotBlank
    @Column(unique = true) // Garante que o nome da categoria seja único
    String nome;

    @NotNull
    String descricao; // Descrição opcional da categoria
}
