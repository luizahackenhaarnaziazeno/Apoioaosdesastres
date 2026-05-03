

/*Inserts de eventos*/
INSERT INTO evento (codigo, descricao, data, latitude, longitude) VALUES
(1, 'Terremoto de magnitude 7.8', '2024-11-15', -9.1900, -77.0353);

INSERT INTO evento (codigo, descricao, data, latitude, longitude) VALUES
(2, 'Ciclone Tropical', '2024-10-22', -23.5505, 46.6333);

INSERT INTO evento (codigo, descricao, data, latitude, longitude) VALUES
(3, 'Enchente devido a fortes chuvas', '2024-11-10', -20.3120, -40.3125);

INSERT INTO evento (codigo, descricao, data, latitude, longitude) VALUES
(4, 'Deslizamento de Terra', '2024-10-18', -22.9157, -43.1896);

INSERT INTO evento (codigo, descricao, data, latitude, longitude) VALUES
(5, 'Furacão com ventos de 250 km/h', '2024-09-28', 18.2570, -77.0164);


/* data de inicio esta saindo estranha*/

/*Inserts de atendimentos*/
INSERT INTO atendimento (cod,evento_codigo,datainicio,duracao,status) VALUES  (1,1,'2024-11-15', 120, 'Pendente');
INSERT INTO atendimento (cod,evento_codigo,datainicio,duracao,status) VALUES  (2,2,'2024-10-22', 60, 'Concluído');
INSERT INTO atendimento (cod,evento_codigo,datainicio,duracao,status) VALUES  (3,3,'2024-11-10', 90, 'Em andamento');

/*Inserts de equipes*/
/*INSERT INTO equipe (numero,equipamento_numero,quantidade, latitude, longitude) VALUES (1,1, 5, -30.0277, -51.2287);

INSERT INTO equipe (numero,equipamento_numero, quantidade, latitude, longitude) VALUES (2,2, 5, -35.183, -56.1434);

INSERT INTO equipe (numero,equipamento_numero, quantidade, latitude, longitude) VALUES (3,3, 5, -23.5505, -46.6333);

INSERT INTO equipe (numero,equipamento_numero, quantidade, latitude, longitude) VALUES (4,4,5, -22.9068, -43.1729);

INSERT INTO equipe (numero,equipamento_numero, quantidade, latitude, longitude) VALUES (5,5, 5, -15.7942, -47.8825);*/

/*Inserts de equipamentos */
/*
INSERT INTO equipamento (id, nome, custoDiario) VALUES (1, 'Gerador', 50.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (2, 'Lanterna', 5.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (3, 'Bomba de Água', 25.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (4, 'Kit de Primeiros Socorros', 15.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (5, 'Lona', 10.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (6, 'Rádio Comunicador', 10.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (7, 'Corda', 5.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (8, 'Motosserra', 35.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (9, 'Bote Inflável', 45.0);

INSERT INTO equipamento (id, nome, custoDiario) VALUES (10, 'Cobertor Térmico', 8.0);
*/