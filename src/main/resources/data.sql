INSERT INTO categorie (nom) VALUES
('Travail'),
('Personnel'),
('Formation');

INSERT INTO tache (titre, description, statut, date_fin, categorie_id) VALUES
('Apprendre Spring Boot', 'Créer une API REST complète', 'EN_COURS', '2026-03-15', 3),
('Créer le frontend React', 'Consommer l API avec fetch/Axios', 'A_FAIRE', '2026-03-30', 3),
('Dockeriser l application', 'docker-compose', 'TERMINE', NULL, 1);

INSERT INTO user (username, email, password) VALUES
('root', 'root@root', '$2a$10$I395P5FTxJGA1e1iTVyEmuwSn77vYIK94If3sztdGkXJmfmi4FGtu'),
('foo', 'foo@foo', '$2a$10$9G9cC3qAo1wKCuJT7TZxReylflPTiBmTGpjk8EqbZnVJPrd2bqVOi'),
('bar', 'bar@bar', '$2a$10$twz.uMvQqK0y0uphd.3EFuBo9IQXFlbJDrMCI7UsSupoXrX9GA8Ye');
