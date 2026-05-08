/* 1. Inserts de eventos (Não dependem de ninguém) */
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


/* 2. Inserts de equipes */
INSERT INTO equipe (numero, quantidade, latitude, longitude) VALUES (1, 5, -30.0277, -51.2287);
INSERT INTO equipe (numero, quantidade, latitude, longitude) VALUES (2, 5, -35.183, -56.1434);
INSERT INTO equipe (numero, quantidade, latitude, longitude) VALUES (3, 5, -23.5505, -46.6333);
INSERT INTO equipe (numero, quantidade, latitude, longitude) VALUES (4, 5, -22.9068, -43.1729);
INSERT INTO equipe (numero, quantidade, latitude, longitude) VALUES (5, 5, -15.7942, -47.8825);


/* 3. Inserts de equipamentos (Assumindo que o ID é gerado automaticamente pelo banco/ORM) */
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Gerador', 50.0, 1);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Lanterna', 5.0, 1);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Bomba de Água', 25.0, 2);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Kit de Primeiros Socorros', 15.0, 3);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Lona', 10.0, 4);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Rádio Comunicador', 10.0, 5);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Corda', 5.0, 1);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Motosserra', 35.0, 2);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Bote Inflável', 45.0, 3);
INSERT INTO equipamento (nome, custo_diario, equipe_numero) VALUES ('Cobertor Térmico', 8.0, 4);


/* 4. Inserts de atendimentos 
   Nota: Adicionada a referência 'equipe_numero' para refletir o comportamento do status. */
   
-- Atendimento 1: PENDENTE (Nenhuma equipe alocada ainda)
INSERT INTO atendimento (cod, evento_codigo, equipe_numero, datainicio, duracao, status) 
VALUES (1, 1, NULL, '2024-11-15 08:30:00', 120, 'PENDENTE');

-- Atendimento 2: FINALIZADO (Exige uma equipe alocada, usando equipe 1)
INSERT INTO atendimento (cod, evento_codigo, equipe_numero, datainicio, duracao, status) 
VALUES (2, 2, 1, '2024-10-22 14:00:00', 60, 'FINALIZADO');

-- Atendimento 3: EXECUTANDO (Exige uma equipe alocada, usando equipe 2)
INSERT INTO atendimento (cod, evento_codigo, equipe_numero, datainicio, duracao, status) 
VALUES (3, 3, 2, '2024-11-10 09:15:00', 90, 'EXECUTANDO');