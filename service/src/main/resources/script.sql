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