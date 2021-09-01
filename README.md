# [RealWorld](https://github.com/gothinkster/realworld) implementation using Spring Boot with Kotlin

# In progress... 🏗️

[![CI](https://github.com/brunohenriquepj/spring-boot-kotlin-realworld/actions/workflows/ci-action.yml/badge.svg)](https://github.com/brunohenriquepj/spring-boot-kotlin-realworld/actions/workflows/ci-action.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=brunohenriquepj_spring-boot-kotlin-realworld&metric=alert_status)](https://sonarcloud.io/dashboard?id=brunohenriquepj_spring-boot-kotlin-realworld)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=brunohenriquepj_spring-boot-kotlin-realworld&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=brunohenriquepj_spring-boot-kotlin-realworld)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=brunohenriquepj_spring-boot-kotlin-realworld&metric=coverage)](https://sonarcloud.io/dashboard?id=brunohenriquepj_spring-boot-kotlin-realworld)
[![codecov](https://codecov.io/gh/brunohenriquepj/spring-boot-kotlin-realworld/branch/main/graph/badge.svg?token=C1VU2VM563)](https://codecov.io/gh/brunohenriquepj/spring-boot-kotlin-realworld)

## Setup Java with [SDKMAN!](https://github.com/sdkman/sdkman-cli)

```console
sdk env install
```

---

## Build

```console
./gradlew build --exclude-task test
```

---

## Test

```console
./gradlew test --stacktrace
```

---

## Run api

```console
SPRING_PROFILES_ACTIVE=local ./gradlew bootRun
```

- Go to [swagger](http://localhost:8080/swagger-ui/index.html) or [actuator](http://localhost:8081/actuator)

### Links:

- [Spring Boot Test - Dynamic Property Sources](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-ctx-management-dynamic-property-sources)
