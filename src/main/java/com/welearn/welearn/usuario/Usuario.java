package com.welearn.welearn.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.welearn.welearn.validation.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_usuario;

    @NotNull
    String nome;

    @Email
    String email;

    @NotBlank
    @Size
    String senha;

    @CPF
    @NotBlank
    String cpf;

    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    LocalDate data_nascimento;

    @TipoUsuario
    String tipoUsuario; //ALUNO ou INSTRUTOR
}
