package com.welearn.welearn.curso;

import com.welearn.welearn.categoria.Categoria;
import com.welearn.welearn.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_curso;

    @NotBlank
    String nome;

    @NotBlank
    String descricao;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    Usuario instrutor;
}
