
INSERT INTO categorie (nom) VALUES
('Travail'),
('Personnel'),
('Formation');

INSERT INTO tache (titre, description, statut, date_fin, categorie_id) VALUES
('Apprendre Spring Boot', 'Créer une API REST complète', 'EN_COURS', '2026-03-15', 3),
('Créer le frontend React', 'Consommer l API avec fetch/Axios', 'A_FAIRE', '2026-03-30', 3),
('Dockeriser l application', 'docker-compose', 'TERMINE', NULL, 1);