-- DROP DATABASE gerenciador_clientes;

CREATE DATABASE gerenciador_clientes;
USE gerenciador_clientes;

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(127) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);