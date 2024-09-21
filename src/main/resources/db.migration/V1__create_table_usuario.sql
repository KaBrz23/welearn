CREATE TABLE usuario (
                         id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255),
                         email VARCHAR(255),
                         senha VARCHAR(255),
                         cpf VARCHAR(14),
                         data_nascimento DATE,
                         tipoUsuario VARCHAR(10)
);
