INSERT INTO DONO(nome, email, idade, endereco)
VALUES('Beatriz', 'beatriz@email.com', '20', 'Rua das Flores, 10');

INSERT INTO DONO(nome, email, idade, endereco)
VALUES('José', 'jose@email.com', '23', 'Rua Vertentes, 26');

INSERT INTO PET(nome, idade, tipo_pet, dono_id)
VALUES('Nego', '6', '1', '1');

INSERT INTO PET(nome, idade, tipo_pet, dono_id)
VALUES('Neguinho', '5', '1', '1');

INSERT INTO PET(nome, idade, tipo_pet, dono_id)
VALUES('Pantera', '1', '2', '1');

INSERT INTO PET(nome, idade, tipo_pet, dono_id)
VALUES('Dolly', '8', '1', '2');

INSERT INTO VETERINARIO(nome, email, idade, especialidade)
VALUES('Paulo Silva', 'psilva@email.com', '24', 'radiologia');

INSERT INTO CONSULTA(pet_id, vet_id, data_horario, descricao)
VALUES('1', '1', '2021-03-10 13:00', 'raio x do torax');
