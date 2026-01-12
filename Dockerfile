#Stage 1: Build
FROM ghcr.io/graalvm/native-image-community:21 AS builder
RUN microdnf install findutils gcc glibc-devel zlib-devel -y

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
RUN chmod +x ./gradlew

COPY . .
RUN ./gradlew nativeCompile --no-daemon --scan

#Stage 2: Create final image with only runtime and the jar
FROM gcr.io/distroless/cc-debian12
WORKDIR /app
#Copy the binary compiled from the builder stage
COPY --from=builder /app/build/native/nativeCompile/* /app/greenhouse-kt

EXPOSE 8080
ENTRYPOINT ["/app/greenhouse-kt"]