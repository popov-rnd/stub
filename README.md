# Latency Stub Service

A minimal Spring Boot service used to inject controlled latency during performance experiments.

This service simulates a remote downstream dependency (e.g., database, REST API, S3, or cross-service call) by introducing a fixed delay before responding.

It is not part of the benchmark comparison.
Its only purpose is to amplify and control IO-bound behavior in upstream services.

## Purpose

This stub is designed to:

- Simulate IO-bound downstream latency
- Provide deterministic N ms delay
- Avoid becoming a performance bottleneck
- Enable clean concurrency benchmarking

## Notes

- Virtual Threads are enabled to avoid thread exhaustion.
- Logging is reduced to prevent performance interference.
- No additional business logic should be added.

# Packaging into a Docker/OCI container

Build an OCI image via *Buildpacks* (recommended for most cases)

Spring Boot Maven plugin can build an OCI image directly from your jar using *Cloud Native Buildpacks*:

```
./mvnw spring-boot:build-image \
  -Dspring-boot.build-image.imageName=stub:jvm
```

This runs the Maven package lifecycle and produces an image without you writing a Dockerfile.

It produces a *Docker/OCI* image directly inside your local Docker daemon.

To build the image with *spring-boot:build-image*, you need Docker ***installed*** and ***running****.

Verify by running:

```
docker images
```

See more at [docs](https://docs.spring.io/spring-boot/maven-plugin/build-image.html)

*Paketo* explicitly states as default JVM provider:

- The Java Buildpack uses the ***BellSoft Liberica*** impl-s of the JRE and JDK. JVM installation is handled by the BellSoft Liberica Buildpack. The JDK will be installed in the build container but only the JRE will be contributed to the application image.

See more at: [docs](https://paketo.io/docs/reference/java-reference)

# Running

```
docker run --rm -p 8081:8081 stub:jvm
```