# How to start and use the system

## Run Backend on PROD

<details>
<summary>Run Backend on PROD </summary>

<ul>
  <li>Start in the root directory of this project.</li>
  <li>Run this shell script:
    <ul>
      <li>Linux/Mac: 
        <pre><code>sh run.sh</code></pre>
      </li>
      <li>Windows: 
        <pre><code>./run.sh</code></pre>
      </li>
    </ul>
  </li>
  <li>Press `y/yes` to build the entire project:</li>
</ul>

<figure>
    <img src="images/deployment/run-sh-1.png" width="80%">
</figure>

<ul>
  <li>After the build finishes, press `5` to run all docker-compose files:</li>
</ul>

<figure>
    <img src="images/deployment/run-sh-2.png" width="80%">
</figure>

<ul>
  <li>Now we can see the services running:</li>
</ul>

<figure>
    <img src="images/deployment/docker-services.png" width="80%">
</figure>

<ul>
  <li>Go to the browser: <a href="http://localhost/dashboard/" target="_blank">http://localhost/dashboard/</a></li>
</ul>

<ul>
  <li>Insert these credentials:
    <ul>
      <pre><code>user</code></pre>
      <pre><code>123</code></pre>
    </ul>
  </li>
  <li>Here is the dashboard on PROD:</li>
</ul>

<figure>
    <img src="images/deployment/traefik-dashboard.png" width="80%">
</figure>

<ul>
  <li>To initialize the database with data needed in the app, run:
    <ul>
      <li>Linux/Mac: 
        <pre><code>sh project-script.sh</code></pre>
      </li>
      <li>Windows: 
        <pre><code>./project-script.sh</code></pre>
      </li>
    </ul>
  </li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

## DEV-UI

<details>
<summary> DEV-UI </summary>


<ul>
  <li><pre><code>http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui</code></pre></li>
  <li><pre><code>http://localhost:8080/q/dev-ui/io.quarkus.quarkus-kafka-client/topics</code></pre></li>
  <li><pre><code>http://localhost:8080/dashboard/#/</code></pre></li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

## Quarkus-UI in PROD

<details>
<summary> Quarkus-UI in PROD </summary>

<ul>
  <li><pre><code>http://localhost/dashboard/#/</code></pre></li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>
</details>

## Prometheus & Grafana in PROD

<details>
<summary> Prometheus & Grafana in PROD </summary>

<ul>
  <li>Traefik-Dashboard: <pre><code>http://localhost/dashboard/</code></pre></li>
  <li>Prometheus: <pre><code>http://localhost/prometheus</code></pre></li>
  <li>Grafana: <pre><code>http://localhost/metrics-ui-service/login</code></pre></li>
  <li>Jaeger-Tracing: <pre><code>http://localhost/tracing/search</code></pre></li>
</ul>

<p align="right">(<a href="#top">back to top</a>)</p>
</details>



