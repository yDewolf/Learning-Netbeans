CREATE DATABASE locadora_bikes;
USE locadora_bikes;

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(127),
    cpf CHAR(14),
    telefone VARCHAR(20),
    email VARCHAR(127)
);

CREATE TABLE bicicleta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo CHAR(10) UNIQUE,
    status ENUM('disponível', 'locada', 'manutenção') DEFAULT 'disponível'
);

CREATE TABLE manutencao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bicicleta_id INT NOT NULL,
    descricao TEXT,
    data DATE NOT NULL,
    FOREIGN KEY (bicicleta_id) REFERENCES bicicleta(id)
);

CREATE TABLE locacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    bicicleta_id INT NOT NULL,
    data_inicio DATETIME DEFAULT CURRENT_DATE(),
    data_fim DATETIME NOT NULL,
    status ENUM('ativa', 'finalizada'),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (bicicleta_id) REFERENCES bicicleta(id)
);
