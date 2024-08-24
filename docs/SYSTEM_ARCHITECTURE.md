# Architecture Design Overview

![Microservice Architecture](docs/diagrams/software-archicture/Micro-Service-Architektur-Final.png)

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
    <img src="images/Treaffik-UI-Ziwschenstand.png" width="80%">
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
    <img src="images/login-service.png" width="80%">
    <figcaption>Endpoints of Login-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/Depot-Service.png" width="80%">
    <figcaption>Endpoints of Depot-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/transaction-service.png" width="80%">
    <figcaption>Endpoints of Transaction-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/customer-infomation-service.png" width="80%">
    <figcaption>Endpoints of Customer-Information-Service</figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/support-service.png" width="80%">
    <figcaption>Endpoints of Support-Service</figcaption>
</figure>
</div>


## Jaeger UI / Tracing

If started with the **_-t_** flag, have a look at the Jaeger UI.

> **Path:** [http://localhost/tracing](http://localhost/tracing)

Jaeger is our OpenTelemetry compliant service that collects tracing information from all services comprising our system.
With that data, one is able to examine how requests _move_ through the system, gather insights about processing times as
well as errors that happen along the way.

<div style="text-align: center;">

<figure>
    <img src="assets/images/screen_jaeger_trace_list.png" width="80%">
    <figcaption>Jaeger main screen showing captured traces</figcaption>
</figure>

<figure>
    <img src="assets/images/screen_jaeger_trace_graph.png" width="80%">
    <figcaption>Jaeger showing a trace graph, e.g. the dependencies between different spans</figcaption>
</figure>

<figure>
    <img src="assets/images/screen_jaeger_span_info.png" width="80%">
    <figcaption>Jaeger showing detail of a trace & its spans</figcaption>
</figure>

</div>


## Prometheus / Metrics

If started with the **_-m_** flag, have a look at Prometheus, the system that aggregates metric data from all services (
e.g. our own Quarkus services, databases, Traefik and, yes, Prometheus itself).

> **Path:** [http://localhost/prometheus](http://localhost/prometheus)

<div style="text-align: center;">
    <figure>
        <img src="assets/images/screen_prometheus_graph.png" width="80%">
        <figcaption>Prometheus showing bytes used by the JVM</figcaption>
    </figure>
</div>

