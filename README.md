# Delivery-Tracker

## Project Overview

**Delivery-Tracker** is a microservices-based system that simulates order processing. It consists of three Spring Boot services:

* **Order Service** – handles order creation and publishes events.
* **Payment Service** – listens for order events, processes payments, and emits payment events.
* **Delivery Service** – reacts to payment events to manage deliveries.

Services communicate asynchronously via **Apache Kafka**. Each has its own **PostgreSQL** database, and all are containerized using **Docker Compose**. A shared module `common-libs` contains event classes and is used by all services.

**Tech stack:** Spring Boot, Kafka, PostgreSQL, Docker Compose, Maven.

---

## Getting Started

### Prerequisites

* Java 17+
* Maven
* Docker & Docker Compose

### Build the Project

```bash
mvn clean package -DskipTests
```

> Ensure `common-libs` builds first so other services can reference it.

### Run with Docker Compose

```bash
docker-compose up --build
```

This command will build all services and start:

* Kafka + ZooKeeper
* PostgreSQL (per service)
* Order / Payment / Delivery services

Use `docker-compose ps` to confirm all containers are running.

---

## Verifying the Setup

* Check logs:

  ```bash
  docker-compose logs order-service
  docker-compose logs payment-service
  docker-compose logs delivery-service
  ```

* Access services:

  * Order: `http://localhost:8081`
  * Payment: `http://localhost:8082`
  * Delivery: `http://localhost:8083`

* Kafka should be up at `kafka:9092` (internal).

* PostgreSQL containers should log "ready to accept connections".

---

## Tips

* To rebuild after changes:

  ```bash
  mvn clean package -DskipTests
  docker-compose up --build
  ```
* To stop:

  ```bash
  docker-compose down
  ```

---

For more advanced configuration or event testing, see the code in each service.

---

**Author**: Yuliia Kopytko
