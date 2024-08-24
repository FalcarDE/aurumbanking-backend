# Architecture Design Overview

## Backend Architecture

<details>
<summary> Backend Architecture </summary>

<figure>
    <img src="diagrams/software-archicture/Micro-Service-Architektur-Final.png" width="100%">
    <figcaption>Entire Backend Architecture</figcaption>
</figure>


<p>Our architecture design follows a microservices approach, with Traefik and Zookeeper serving as load balancers. The system comprises the following microservices:</p>

<ul>
    <li><strong>login-service</strong></li>
    <li><strong>customer-information-service</strong></li>
    <li><strong>depot-service</strong></li>
    <li><strong>transaction-service</strong></li>
    <li><strong>support-service</strong></li>
</ul>

<p>All services are developed using Kotlin (JDK 17) and Quarkus.</p>

<div class="back-to-top"><p>(<a href="#top">back to top</a>)</p></div>

</details>

## Kafka Integration

<details>
<summary> Kafka Integration </summary>


<p> 
The <code>depot-service</code> and <code>transaction-service</code> share a Kafka topic named <code>update-depot-value</code>. When a new transaction is
initiated, it is published to the `update-depot-value` Kafka topic and subsequently consumed by the <code>depot-service</code>. 
For more details read <a href="http://127.0.0.1:4242/KAFKA.html" target="_blank"> Kafka-Integration </a> 
</p>

</details>

## Database Management

<details>
<summary> Database Management </summary>
PostgreSQL is used as the database management system.

<p>Specifically:</p>
        <ul>
            <li><code>login-service</code> and <code>customer-information-service</code> access the <code>customer-information-service-db</code>.</li>
            <li><code>depot-service</code> and <code>transaction-service</code> use the <code>depot-service-db</code>.</li>
            <li><code>support-service</code> utilizes the <code>support-service-db</code>.</li>
        </ul>

<p>Here are the database models of our databases:</p>

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

</details>

## Monitoring

<details>
<summary> Monitoring </summary>

<p>
For monitoring purposes, 
Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards. 
For more information, read this <a href="http://127.0.0.1:4242/PROMETHEUS_AND_GRAFANA.html" target="_blank">Prometheus-Grafana</a>.
</p>

</details>

## Traefik API Gateway

<details>
<summary> Traefik API Gateway </summary>

<p> Take a look at the Traefik dashboard on the Prod-Enviroment. </p>

<code> <a href="http://localhost/dashboard/" target="_blank"> http://localhost/dashboard/ </a> </code>

<p> 
Traefik acts as API gateway and load balancer.
It's configured in the _docker-compose-prod.yml_ and picks up running containers automatically.
Thus, it will recognize containers that are started or stopped while the system is already running.
<p> 

<div style="text-align: center;">
<figure>
    <img src="images/abschlusspraesentation/Treaffik-UI-Ziwschenstand.png" width="80%">
    <figcaption>Traefik Dashboard</figcaption>
</figure>
</div>

</details>

## Endpoints with Swagger-UI

### Swagger UI with Traefik in Dev Mode

<details>
<summary> Swagger UI with Traefik in Dev Mode </summary>

<p>
Traefik is a powerful reverse proxy and load balancer that can automatically discover and manage services.
In development mode, Traefik comes with built-in support for Swagger UI, a popular tool for visualizing and interacting
with APIs.
</p>

<p>
Once Traefik is running in dev-mode, you can access the Swagger UI by navigating to the following URL in your web
browser:
</p>

<code> <a href="http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui" target="_blank"> http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui </a> </code>
<p>
In the following you can see all the endpoints of our services:
</p>

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
</details>




