CREATE TABLE curso (
                       id_curso BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(255),
                       descricao VARCHAR(255),
                       id_categoria BIGINT,
                       FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
                       id_usuario BIGINT,
                       FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);