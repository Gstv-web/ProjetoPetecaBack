CREATE DATABASE db_projetoIntegrador;

USE db_projetoIntegrador;

CREATE TABLE tb_user (
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
razao_social VARCHAR (100),
email VARCHAR(50),
senha VARCHAR (10),
tipo ENUM ("INSTITUICOES", "EMPRESA", "GOV"),
localidade VARCHAR (100),
contato VARCHAR (100)
);

CREATE TABLE tb_postagem (
id_postagem  INT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR (40),
descricao VARCHAR (250),
endereco VARCHAR (100),
tipo VARCHAR (20),
demanda ENUM("Oferta", "Procura"),
visualizacao VARCHAR (20),
data_publicacao DATETIME, 
fk_usuario INT,
FOREIGN KEY (fk_usuario) REFERENCES tb_user (id_usuario)
);