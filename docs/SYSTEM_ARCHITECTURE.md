# Architecture Design Overview

![Microservice Architecture](diagrams/software-archicture/Micro-Service-Architektur-Final.png)

Our architecture design follows a microservices approach, with Traefik and Zookeeper serving as load balancers. The system comprises the following microservices:

- **login-service**
- **customer-information-service**
- **depot-service**
- **transaction-service**
- **support-service**

All services are developed using Kotlin (JDK 17) and Quarkus.

## Kafka Integration

The `depot-service` and `transaction-service` share a Kafka topic named `update-depot-value`. When a new transaction is initiated, it is published to the `update-depot-value` Kafka topic and subsequently consumed by the `depot-service`.

## Database Management

PostgreSQL is used as the database management system. Specifically:
- The `login-service` and `customer-information-service` access the `customer-information-service-db`.
- The `depot-service` and `transaction-service` use the `depot-service-db`.
- The `support-service` utilizes the `support-service-db`.

Here are the database-model of our databases:

<div style="display: flex; justify-content: center; gap: 20px;">

<div style="text-align: center; width: 40%;">
<figure>
    <img src="images/datanbase-model/customer-information-database-entity.png" style="width: 100%;">
    <figcaption>Customer-Information-Database Entity</figcaption>
</figure>
</div>

<div style="text-align: center; width: 40%;">
<figure>
    <img src="images/datanbase-model/depot-database-entity.png" style="width: 100%;">
    <figcaption>Depot-Database Entity</figcaption>
</figure>
</div>

</div>


<div style="display: flex; justify-content: center; gap: 20px;">

<div style="text-align: center; width: 40%;">
<figure>
    <img src="images/datanbase-model/transaction-database-entity.png" style="width: 100%;">
    <figcaption>Transaction-Database Entity</figcaption>
</figure>
</div>

<div style="text-align: center; width: 40%;">
<figure>
    <img src="images/datanbase-model/support-database-entity.png" style="width: 100%;">
    <figcaption>Support-Database Entity</figcaption>
</figure>
</div>

</div>


## Monitoring

For monitoring purposes, Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards.


## Traefik API Gateway

Take a look at the Traefik dashboard on the Prod-Enviroment.

> **Path:** [http://localhost/dashboard/](http://localhost/dashboard/)

Traefik acts as API gateway and load balancer.
It's configured in the _docker-compose-prod.yml_ and picks up running containers automatically.
Thus, it will recognize containers that are started or stopped while the system is already running.

<div style="text-align: center;">
<figure>
    <img src="images/abschlusspraesentation/Treaffik-UI-Ziwschenstand.png" width="80%">
    <figcaption>Traefik Dashboard</figcaption>
</figure>
</div>



## Endpoints with Swagger-UI

### Swagger UI with Traefik in Dev Mode

Traefik is a powerful reverse proxy and load balancer that can automatically discover and manage services.
In development mode, Traefik comes with built-in support for Swagger UI, a popular tool for visualizing and interacting with APIs.
Once Traefik is running in dev-mode, you can access the Swagger UI by navigating to the following URL in your web browser:

> **Path:**  http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui

In the following you can see all the endpoints of our services:

<div style="text-align: center;">
<figure>
    <img src="images/backend-services/login-service/login-service.png" width="80%">
    <figcaption>Endpoints of Login-Service</figcaption>
</figure>
</div>


<div style="text-align: center;">
<figure>
    <img src="images/backend-services/customer-information-service/endpoints-customer-information-service.png" width="80%">
    <figcaption>Endpoints of Customer-Information-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/backend-services/depot-service/endpoints-depot-service.png" width="80%">
    <figcaption>Endpoints of Depot-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/backend-services/transaction-service/endpoint-transaction-service.png" width="80%">
    <figcaption>Endpoints of Transaction-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/backend-services/support-service/endpoint-support-service.png" width="80%">
    <figcaption>Endpoints of Support-Service</figcaption>
</figure>
</div>




