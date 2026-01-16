# Your Car Your Way - WebSocket POC HELP

## Overview

This POC demonstrates a **WebSocket implementation** for the **Your Car Your Way** application.  
It allows real-time communication between the server and clients, e.g., for live updates on reservations, messages, or car availability.

---

## Requirements

- **Java 17** (or higher)
- **Maven 3.6+**
- **MySQL 8+** database
- Environment variables for database connection:
  - `APP_DB_HOST` – Database host
  - `APP_DB_PORT` – Database port
  - `APP_DB_NAME` – Database name
  - `APP_DB_USER` – Database username
  - `APP_DB_PASSWORD` – Database password

---

## Installation & Setup

1. **Clone the repository**

```bash
git clone https://github.com/MPorret/p13_definissez_une_solution_fonctionnelle_et_concevez_l_architecture_d_une_application
cd p13_definissez_une_solution_fonctionnelle_et_concevez_l_architecture_d_une_application/service
```

## Running the project

```bash
mvn clean install
mvn spring-boot:run
```

### SQL Script

    CREATE TABLE `USERS` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `email` varchar(62) NOT NULL,
        `lastname` varchar(50),
        `firstname` varchar(50),
        `password` varchar(255) NOT NULL,
        `address`varchar(95),
        `phone` varchar(20),
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

    CREATE TABLE `MESSAGES` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `author_id` integer,
        `reclamation_id` integer,
        `message` varchar(2000),
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

    CREATE TABLE `RECLAMATIONS` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `user_id` integer,
        `statut` varchar(50),
        `reservation_id` integer,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

    CREATE TABLE `RESERVATIONS` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `mileage` integer,
        `start_location` Date,
        `end_location` Date,
        `cost` integer,
        `car_id` integer,
        `user_id` integer,
        `canceled` TINYINT(1) DEFAULT 0,
        `payment_id` integer
    );

    CREATE TABLE `CARS` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `model_id` integer,
        `cost` integer,
        `agency_id` integer,
        `image` varchar(2083)
    );

    CREATE TABLE `MODELS` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `name` varchar(50),
        `category` varchar(10)
    );

    CREATE TABLE `AGENCIES` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `name` varchar(100),
        `address` varchar(95)
    );

    CREATE TABLE `PAYMENTS` (
        `id` integer PRIMARY KEY AUTO_INCREMENT,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `cost` integer,
        `description` varchar(95)
    );

    CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

    ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`author_id`) REFERENCES `USERS` (`id`);

    ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`reclamation_id`) REFERENCES `RECLAMATIONS` (`id`);

    ALTER TABLE `RECLAMATIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

    ALTER TABLE `RECLAMATIONS` ADD FOREIGN KEY (`reservation_id`) REFERENCES `RESERVATIONS` (`id`);

    ALTER TABLE `RESERVATIONS` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

    ALTER TABLE `RESERVATIONS` ADD FOREIGN KEY (`car_id`) REFERENCES `CARS` (`id`);

    ALTER TABLE `RESERVATIONS` ADD FOREIGN KEY (`payment_id`) REFERENCES `PAYMENTS` (`id`);

    ALTER TABLE `CARS` ADD FOREIGN KEY (`model_id`) REFERENCES `MODELS` (`id`);

    ALTER TABLE `CARS` ADD FOREIGN KEY (`agency_id`) REFERENCES `AGENCIES` (`id`);
