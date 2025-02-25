# Metrics - Service  


## Prometheus 
<details>
<summary> Prometheus </summary>

<p> Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards. Follow this Link on PROD to Web-UI of Prometheus: </p>

<code> <a href="http://localhost/prometheus" target="_blank"> http://localhost/prometheus </a> </code>


<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/prometheus-entry.png" width="80%">
        <figcaption>Prometheus entry by given path.</figcaption>
    </figure>
</div>

Here are some examples which metrics can be provided by Prometheus and can be shown on the Prometheus-Graphs.


<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/metrics-example.png" width="80%">
        <figcaption>Collection of Traefik-Entrypoint Metrics</figcaption>
    </figure>
</div>


<div style="text-align: center;">
    <figure>
        <img src="images/prometheus/metrics-graph-example.png" width="80%">
        <figcaption>Collection of Traefik-Entrypoint Metrics</figcaption>
    </figure>
</div>

</details>


## Grafana - Monitoring

<details>
<summary> Grafana </summary>

<p> For monitoring purposes, Traefik provides metrics that are collected by a Prometheus server and visualized through Grafana dashboards. Follow this Link on PROD to Web-UI of Grafana: </p>

<code> <a href="http://localhost/metrics-ui-service/login" target="_blank"> http://localhost/metrics-ui-service/login </a> </code>

<code> username: admin </code>

<code>  password: admin </code>

<p> We have for each of our services a monitoring dashboards. In the following section, we will have a closer look on those dashboards. </p>

<h3> Connect Prometheus with Grafana </h3>

<p> To enable Grafana to capture metrics from Prometheus, Prometheus first needs to be connected. To achieve this, we need to run all Docker containers in the PROD environment. </p>

<p> After executing this, you need to access <a href="localhost/dashboards/ " target="_blank">localhost/dashboards/ </a> to retrieve the IP address of the <strong<metrics-service@docker</strong> service. </p>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom.png" width="80%">
    <figcaption>IP-Address of smetrics-service@docker </figcaption>
</figure>
</div>

<p> Now, we need to connect the data source in Grafana by creating a new Prometheus connection and linking it using the retrieved IP address. </p>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-1.png" width="80%">
    <figcaption>Using the IP-Address for the Prometheus Backend Connection </figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-3.png" width="80%">
    <figcaption>Successful Connection </figcaption>
</figure>
</div>

<p> Now, we are able to create dashboards by using the provided metrics from the prometheus server. </p>


<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-4.png" width="80%">
    <figcaption>Add new Dashbaord </figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-5.png" width="80%">
    <figcaption> Connect to the establish prometheus server </figcaption>
</figure>
</div>

<div style="text-align: center;">
<figure>
    <img src="images/prometheus/connect-grafa-prom-6.png" width="80%">
    <figcaption> Using services metrics from Prometheus Server </figcaption>
</figure>
</div>

</details>


### Login-Service

<details>
<summary> Login-Service </summary>
    
    <p>This dashboard provides a clear visualization of key performance metrics related to the login service, using Prometheus as the data source. Each panel is designed to monitor a specific aspect of the service, helping ensure reliability and performance optimization.</p>
    
    <h3> Panel 1: Sum Server Request By Second </h3>
    
    This line chart tracks the total number of GET requests per second on the login service. It helps monitor traffic patterns and identify potential issues, such as increased load that may require resource scaling.
    
    <div style="text-align: center;">
        <figure>
            <img src="images/grafana-dashboards/login-service/grafana-sum-of-request-second.png" width="70%">
            <figcaption>Screenshot showing the total number of login requests per second</figcaption>
        </figure>
    
    </div>
    
    <h3> Panel 2: Count of Server Requests Per Second </h3>
    
    This gauge displays the current number of server requests per second, helping to quickly assess the real-time load on the service.
    
    <div style="text-align: center;">
        <figure>
            <img src="images/grafana-dashboards/login-service/grafana-count-of-request-second.png" width="70%">
            <figcaption>Screenshot showing the real-time count of server requests per second for the login service.</figcaption>
        </figure>
    
    </div>
    
    <h3> Panel 3: Max of Server Requests by Second </h3>
    
    This line chart visualizes the maximum number of server requests per second over time for the login service. It helps identify peak request rates, enabling the monitoring of potential performance bottlenecks and ensuring that the service can handle traffic spikes effectively.
    
    <div style="text-align: center;">
        <figure>
            <img src="images/grafana-dashboards/login-service/grafana-max-of-request-second.png" width="70%">
            <figcaption>Screenshot showing the maximum number of server requests per second for the login service.</figcaption>
        </figure>
    
    </div>
</details>

### Customer-Information-Service

<details>
<summary> Customer-Information-Service </summary>
### Panel 1: Successful Support Requests Per Request Type (Max Duration)

This gauge panel shows the maximum processing time for successful support requests (HTTP 200 status).
It helps in understanding the performance of the customer information service, particularly in measuring the longest request times.
If the max duration consistently exceeds a certain threshold, this could indicate a performance bottleneck.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/customer-service/grafana_cust_inf_service_max_request_time.png" width="70%">
    <figcaption>Screenshot of the max duration for successful customer information requests</figcaption>
</figure>

</div>

### Panel 2: Sum of Server Errors last 24h

This bar chart  visualizes the total number of customer information request errors handled by the service over time.
It helps troubleshooting in case of system damages be visualizing timezones with a high increase of error requests.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/customer-service/grafana_cust_inf_service_count_server_errors.png" width="70%">
    <figcaption>Screenshot of the sum of server errors last 24h</figcaption>
</figure>

</div>

### Panel 3: Sum of Requests per Hour last 24h

The bar chart diagram displays the sum of server requests per request type per hour. It helps to determine high frequent
interactions in order to have an indicator for improvement areas in case of performance issues.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/customer-service/grafana_cust_inf_service_count_server_requ_per_h.png" width="70%">
    <figcaption>Screenshot sum of requests per request type per hour last 24h</figcaption>
</figure>

</div>

### Panel 4: Sum of Connection Seconds per Half Hour last 24h

This bar chart displays the count of connection seconds per half hour for the depot service. It helps to define time
zones with high server activity for load handling purposes.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/customer-service/grafana_cust_inf_service_sum_connection_seconds_last_24h.png" width="70%">
    <figcaption>Screenshot sum of connection seconds per half hour last 24h</figcaption>
</figure>

</div>
</details>

### Depot-Service

<details>
<summary> Depot-Service </summary>


### Panel 1: Successful Support Requests Per Request Type (Max Duration)

This gauge panel shows the maximum processing time for successful depot requests (HTTP 200 status).
It helps in understanding the performance of the depot service, particularly in measuring the longest request times.
If the max duration consistently exceeds a certain threshold, this could indicate a performance bottleneck.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/depot-service/grafana_depot_service_max_request_time.png" width="70%">
    <figcaption>Screenshot of the max duration for successful depot requests</figcaption>
</figure>

</div>

### Panel 2: Sum of Server Errors last 24h

This bar chart  visualizes the total number of depot request errors handled by the service over time.
It helps troubleshooting in case of system damages be visualizing timezones with a high increase of error requests.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/depot-service/grafana_depot_service_count_server_errors.png" width="70%">
    <figcaption>Screenshot of the sum of server errors last 24h</figcaption>
</figure>

</div>

### Panel 3: Sum of Requests per Hour last 24h

The bar chart diagram displays the sum of server requests per request type per hour. It helps to determine high frequent
interactions in order to have an indicator for improvement areas in case of performance issues.

<div style="text-align: center;">

<figure>
    <img src="images/grafana-dashboards/depot-service/grafana_depot_service_count_server_requ_per_h.png" width="70%">
    <figcaption>Screenshot sum of requests per request type per hour last 24h</figcaption>
</figure>

</div>


</details>

### Transaction-Service
<details>
<summary> Transaction-Service </summary>

<p> This dashboard provides a clear visualization of key performance metrics related to the transaction service, 
using Prometheus as the data source. 
Each panel is designed to monitor a specific aspect of the service, helping ensure reliability and performance optimization.
</p>

<p>
This dashboard captures various operational data from the Transaction Service. 
It primarily monitors the metrics of the service's endpoints. 
These metrics are provided by Quarkus and stored in a Prometheus server. 
The data is then retrieved and displayed here.
</p>

<div style="text-align: center;">
    <figure>
        <img src="images/grafana-dashboards/transaction-service/transaction-dashboard.png" width="70%">
        <figcaption>Dashboard of the Transaction-Service </figcaption>
    </figure>
</div>

</details>

### Support-Service

<details>
<summary> Support-Service </summary>

<p> This dashboard provides a clear visualization of key performance metrics related to the support service, 
using Prometheus as the data source. 
Each panel is designed to monitor a specific aspect of the service, helping ensure reliability and performance optimization. </p>

<h3> Panel 1: Successful Support Requests (Max Duration) </h3>

This gauge panel shows the maximum processing time for successful support requests (HTTP 200 status). It helps in understanding the performance of the support service, particularly in measuring the longest request times. If the max duration consistently exceeds a certain threshold, this could indicate a performance bottleneck.

<div style="text-align: center;">

    <figure>
        <img src="images/grafana-dashboards/support-service/grafana-successfull-requests-max.png" width="70%">
        <figcaption>Screenshot of the max duration for successful support requests</figcaption>
    </figure>

</div>

<h3> Panel 2: Sum of Support Requests Over Time (Timeseries) </h3>

This timeseries panel visualizes the total number of support requests handled by the service over time. Monitoring this data helps detect spikes in request volume, which could indicate periods of high load or unusual activity that might need investigation.

<div style="text-align: center;">

    <figure>
        <img src="images/grafana-dashboards/support-service/grafana-sum-of-requests-timeseries.png" width="70%">
        <figcaption>Screenshot of the sum of support requests over time</figcaption>
    </figure>

</div>

<h3> Panel 3: Max Request Time for Status 200 (Timeseries) </h3>

This timeseries panel tracks the maximum duration of HTTP 200 status requests over time. By monitoring this metric, you can identify trends in performance for successful requests and potentially spot periods of degraded performance before they escalate into more significant issues.

<div style="text-align: center;">

    <figure>
        <img src="images/grafana-dashboards/support-service/grafana-messaurement-of-max-request-time-200-timeseries.png" width="70%">
        <figcaption>Screenshot of the max request time for HTTP 200 status requests</figcaption>
    </figure>

</div>

<h3> Panel 4: Max Request Time for Status 200 </h3>

This panel measures the maximum request time for HTTP 200 status requests. It provides immediate insight into how quickly requests are being processed and can alert the team to performance issues that may require tuning or scaling.

<div style="text-align: center;">

    <figure>
        <img src="images/grafana-dashboards/support-service/grafana-messaurement-of-max-request-time-200.png" width="70%">
        <figcaption>Screenshot of the max request time for HTTP 200 status requests</figcaption>
    </figure>

</div>

<h3> Panel 5: Duration of Server Connections in Seconds </h3>

This gauge panel tracks the duration of server connections in seconds. It is useful for identifying potential issues with connection handling in the support service, particularly if connection times begin to increase unexpectedly, potentially leading to timeouts or degraded service quality.

<div style="text-align: center;">
    
    <figure>
        <img src="images/grafana-dashboards/support-service/grafana-duration-of-server-connections-in-seconds.png" width="70%">
        <figcaption>Screenshot of the duration of server connections</figcaption>
    </figure>

</div>

<h3> Conclusion </h3>
This dashboard offers essential metrics to monitor the performance and reliability of the support service. By keeping track of request times, connection durations, and overall service load, you can ensure that the support service remains responsive and scalable, identifying and addressing potential bottlenecks or performance degradations in a timely manner.

</details>





