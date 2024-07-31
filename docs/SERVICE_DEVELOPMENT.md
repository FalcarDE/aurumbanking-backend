# Service Development

## Building and Running

### Start Your Service in Dev Mode

You can run a single service (e.g. catalog or customer review) in dev mode that enables live coding using:

```shell
./gradlew quarkusDev
```

Your service should now be available at [http://localhost:8080](http://localhost:8080).

> **NOTE:**  Quarkus now ships with a Dev UI, which is available in dev mode only at [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/).

> **NOTE:** Dev Mode allows for hot reloading, e.g. just leave your service running, change code and Quarkus will pick up those changes on the fly.

## REST Endpoints

OpenAPI/Swagger UI is available at [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui).

## Creating Containers

The following examples were taken from catalog service.

### Container Properties
Container group, name and tag are configured in your `application.properties`:

```shell
# Use these three options
quarkus.container-image.group=pmc-cc
quarkus.container-image.name=catalog-service
quarkus.container-image.tag=1.0.0

# Or this one to specify the whole image name
quarkus.container-image.image=pmc-cc/catalog-service:1.0.0
```

### Build JVM Image
To create a container image that runs the JVM version of your service, simply run

```shell
./gradlew build -x test -Dquarkus.container-image.build=true
```

### Build Native Image
To create a container image that runs a native version of your service, just run

```shell
./gradlew build -x test \
  -Dquarkus.native.additional-build-args="--initialize-at-run-time=net.datafaker.service.RandomService" \ 
  -Dquarkus.package.type=native \
  -Dquarkus.native.container-build=true \
  -Dquarkus.container-image.build=true
```

The above command uses a builder image with GraalVM (`quarkus.native.container-build=true` option). No need to install it manually on your machine.

> **NOTE:**  The first argument (`quarkus.native.additional-build-args`) is necessary to circumvent an error during native compilation. The specified class creates a static instance of the `Random` class which would then be fixed during compile time leading to a fixed seed for random number generation. Thus, all random sequences during execution would be the same. By specifying the above option, the `RandomService` class will be initialized at runtime and will thus show its expected behaviour. See https://quarkus.io/guides/native-reference#build-time-vs-run-time-initialization for a explanation of a similar issue.
>
> **ADDITIONAL NOTE & WARNING:** The above solution doesn't seem to work - DataFaker is not able to produce randomized data when using the native image.

#### Manually Building A Native Micro Image (WIP)

```shell
docker build -f src/main/docker/Dockerfile.native-micro -t pmc-cc/catalog-service-micro .
```

### Run Your Image

To start a new container using the image created from the commands above, call

```shell
docker run -i --rm -p 8080:8080 pmc-cc/catalog-service:1.0.0
```

### Container References
- [https://quarkus.io/guides/gradle-tooling](https://quarkus.io/guides/gradle-tooling)
- [https://quarkus.io/guides/container-image](https://quarkus.io/guides/container-image)
- [https://quarkus.io/guides/building-native-image](https://quarkus.io/guides/building-native-image)
- [https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md](https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md)


## Test

### JaCoCo Test Coverage

Service include the Quarkus [JaCoCo](https://github.com/jacoco/jacoco) extension which creates test coverage statistics when building & testing a service.
Statistics can be accessed at `<service-folder>/build/jacoco-report`

----

## Included Quarkus Extensions And Related Guides

- RESTEasy Reactive's REST Client ([guide](https://quarkus.io/guides/rest-client-reactive)): Call REST services reactively
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- SmallRye Fault Tolerance ([guide](https://quarkus.io/guides/smallrye-fault-tolerance)): Build fault-tolerant network services
- SmallRye Health ([guide](https://quarkus.io/guides/smallrye-health)): Monitor service health
- Micrometer metrics ([guide](https://quarkus.io/guides/micrometer)): Instrument the runtime and your application with dimensional metrics using Micrometer.
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
- Micrometer Registry Prometheus ([guide](https://quarkus.io/guides/micrometer)): Enable Prometheus support for Micrometer
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- OpenTelemetry ([guide](https://quarkus.io/guides/opentelemetry)): Use OpenTelemetry to trace services
- Jacoco - Code Coverage ([guide](https://quarkus.io/guides/tests-with-coverage)): Jacoco test coverage support
- WebSockets ([guide](https://quarkus.io/guides/websockets)): WebSocket communication channel support
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- Reactive PostgreSQL client ([guide](https://quarkus.io/guides/reactive-sql-clients)): Connect to the PostgreSQL database using the reactive pattern