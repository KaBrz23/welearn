CREATE TABLE usuario (
    id_usuario BIGINT PRIMARY KEY IDENTITY(1,1),  -- IDENTITY para auto incremento
    nome NVARCHAR(255) NOT NULL,
    email NVARCHAR(255),
    senha NVARCHAR(255) NOT NULL,
    cpf NVARCHAR(14) NOT NULL,  -- Armazenar CPF como string para preservar zeros à esquerda
    data_nascimento DATE NOT NULL,  -- DATE armazena data sem horário
    tipo_usuario NVARCHAR(50),
);

CREATE TABLE categoria (
    id_categoria BIGINT PRIMARY KEY IDENTITY(1,1),  -- IDENTITY para auto incremento
    nome NVARCHAR(255) NOT NULL UNIQUE,  -- Nome único
    descricao NVARCHAR(255) NOT NULL
);

CREATE TABLE curso (
    id_curso BIGINT PRIMARY KEY IDENTITY(1,1),  -- IDENTITY para auto incremento
    nome NVARCHAR(255) NOT NULL,
    descricao NVARCHAR(255) NOT NULL,
    id_categoria BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT fk_instrutor FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

