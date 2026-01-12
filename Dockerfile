#Stage 1: Build
FROM ghcr.io/graalvm/native-image-community:21 AS builder
RUN microdnf install findutils -y

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
RUN chmod +x ./gradlew

COPY src src
RUN ./gradlew nativeCompile --no-daemon --scan

#Stage 2: Create final image with only runtime and the jar
FROM gcr.io/distroless/cc-debian12
WORKDIR /app
#Copy the binary compiled from the builder stage
COPY --from=builder /app/build/native/nativeCompile/* /app/greenhouse-kt
#Add execute permission to the binary
RUN chmod +x ./app/greenhouse-kt

EXPOSE 8080
ENTRYPOINT ["/app/greenhouse-kt"]