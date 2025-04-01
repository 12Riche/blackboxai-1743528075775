# Aide Ménagère - Backend

Backend Java/Spring Boot pour l'application de mise en relation d'aides ménagères.

## Technologies
- Java 17
- Spring Boot 3.1
- Spring Security
- JWT Authentication
- MySQL
- Swagger/OpenAPI 3.0

## Installation

1. Cloner le dépôt
2. Configurer la base de données dans `application.properties`
3. Démarrer l'application :
```bash
mvn spring-boot:run
```

## API Documentation

La documentation Swagger est disponible à :
- http://localhost:8080/api/swagger-ui.html
- http://localhost:8080/api/v3/api-docs

## Tests

Pour lancer les tests :
```bash
mvn test
```

## Structure du projet

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/aidemenage/
│   │   │   ├── config/         # Configurations
│   │   │   ├── controller/     # Contrôleurs API
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── model/          # Entités JPA
│   │   │   ├── repository/     # DAOs
│   │   │   ├── security/       # Configuration sécurité
│   │   │   ├── service/        # Logique métier
│   │   │   └── AideMenageApplication.java
│   │   └── resources/          # Fichiers de configuration
│   └── test/                   # Tests unitaires et d'intégration
└── pom.xml                     # Configuration Maven
```

## Variables d'environnement

| Variable | Description |
|----------|-------------|
| DB_URL | URL de la base de données |
| DB_USER | Utilisateur de la base |
| DB_PASSWORD | Mot de passe de la base |
| JWT_SECRET | Clé secrète pour JWT |
| JWT_EXPIRATION | Durée de validité des tokens (ms) |

## Déploiement

Pour créer un package exécutable :
```bash
mvn clean package