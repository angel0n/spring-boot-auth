CREATE SEQUENCE seq_id
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

CREATE TABLE usuarios (
    usuarioid BIGINT DEFAULT NEXTVAL('seq_id') PRIMARY KEY,
    usuarionome VARCHAR(255) NOT NULL,
    usuarioemail VARCHAR(255) NOT NULL,
    usuariosenha VARCHAR(255) NOT NULL
);