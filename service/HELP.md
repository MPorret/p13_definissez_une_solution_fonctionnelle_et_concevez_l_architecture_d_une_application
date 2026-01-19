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

SQL script for creating the schema is available ```src/main/resources/script.sql```