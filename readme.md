# ToDoList API avec Spring Boot

API REST pédagogique construite avec Spring Boot, MySQL et Docker Compose.

Permet de manipuler et de mettre en pratique du dev dans un environement JAVA.

## Technos

- Java 25 / Spring Boot
- Spring Data JPA + Hibernate 7
- MySQL 8 (via Docker)
- phpMyAdmin (via Docker)
- Maven (wrapper inclus)

## Prérequis

- [JDK 21+](https://adoptium.net) installé et `JAVA_HOME` configuré
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installé
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommandé)

## Lancer le projet

### 1. Cloner le repo

`git clone https://github.com/MathieuTWR/ToDoListAPI.git`

`cd ToDoListAPI`

### 2. Lancement des services docker (mysql + pma)

`docker compose up -d db_api phpmyadmin`

check des conteneurs :

`docker compose ps`

Les services exposés :
- MySQL : localhost:3306
- phpMyAdmin : http://localhost:8082

### 3. Configurer les variables d'environnement

Créer un fichier `.env` à la racine (déjà dans le `.gitignore`) :

```
DB_URL=jdbc:mysql://localhost:3306/demoapijava
DB_USERNAME=demoapijava
DB_PASSWORD=demoapijava
JWT_SECRET=VOTRE_CLE_GENEREE_AVEC_OPENSSL
```

Générer la clé JWT :
```bash
openssl rand -base64 64
```
> Sans ce fichier, Spring Boot utilise les valeurs par défaut
> définies dans `application.properties`.

### 4. RUN de l'API

Via IntelliJ : bouton Run (play) sur `DemoApiApplication.java`
> Vous devez avoir configurer le run dans votre IDE pour run le projet

Via terminal :
`./mvnw spring-boot:run -DskipTests` (attention ici le .env n'est pas charger)

 * API disponible ici : http://localhost:8080

 * PMA disponible ici : http://localhost:8082

>La base de données et les données de l'environement de dev sont initialisées
>automatiquement au démarrage via `schema.sql` et `data.sql`.

## Structure du projet
```
src/
└── main/
    ├── java/com/example/demoapi/
    │   ├── controller/        # Endpoints REST (Taches, Auth)
    │   ├── entity/            # Entités JPA (Tache, User)
    │   ├── repository/        # Interfaces Spring Data JPA
    │   ├── service/           # Logique metier
    │   └── security/          # JWT, Filtres, Config Spring Security
    └── resources/
        ├── schema.sql         # Creation des tables
        ├── data.sql           # Données de test
        └── application.properties
```

### Nettoyer le projet (supprime target/)
`./mvnw clean`

### Compiler et vérifier que tout passe (sans lancer l'appli)
`./mvnw clean verify -DskipTests`

### Regénérer les dépendances (après modification du pom.xml)
`./mvnw dependency:resolve`

### Construire le jar exécutable
`./mvnw clean package -DskipTests`

### Lancer l'application Spring Boot
`./mvnw spring-boot:run -DskipTests`

## Endpoints disponibles
> Utiliser postman ou un autre logiciel qui permet de faire du HTTP

| Méthode | URL                | Description                          | ACCES   |
|---------|--------------------|--------------------------------------|---------|
| POST    | /api/auth/register | Créer un compte utilisateur          | PUBLIC  |
| POST    | /api/auth/login    | Se connecter → retourne un token JWT | PUBLIC  |
| GET     | /api/taches        | Lister toutes les tâches             | PROTEGE |
| GET     | /api/taches/{id}   | Récupérer une tâche                  | PROTEGE |
| POST    | /api/taches        | Créer une tâche                      | PROTEGE |
| PUT     | /api/taches/{id}   | Modifier une tâche                   | PROTEGE |
| DELETE  | /api/taches/{id}   | Supprimer une tâche                  | PROTEGE |


## Problèmes fréquents

### `JAVA_HOME` non défini
_./mvnw : The JAVA_HOME environment variable is not defined correctly_

 1. Installer le JDK 21+ depuis https://adoptium.net
 2. Définir JAVA_HOME dans les variables d'environnement système
 3. Redémarrer le terminal

### MySQL non démarré
_Communications link failure / Connection refused_

Vérifier que Docker Desktop est lancé

Lancer : `docker compose up -d db_api phpmyadmin`

Verifier que le conteneur tourne bien : `docker compose ps`

Vérifier que le `docker-compose.yml` contient bien :
```
  ports:
    - "3306:3306"
```

### Variables d'environnement non résolues
_Driver claims to not accept jdbcUrl, ${DB_URL}_

Spring Boot ne charge pas le `.env` automatiquement

Créer le `.env` à la racine OU utiliser les valeurs
  par défaut dans `application.properties` :
```
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/demoapijava}
```

### `schema.sql` / `data.sql` ignorés par Spring Boot
Vérifier la présence de ces lignes dans `application.properties` :
```  
spring.jpa.hibernate.ddl-auto=none
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```
