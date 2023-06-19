FROM openjdk:17-jdk-alpine as build
WORKDIR /Taskmanager/taskmanager

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-alpine 
VOLUME /tmp
ARG DEPENDENCY=/Taskmanager/taskmanager/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 80
CMD ["./mvnw", "spring-boot:run"]
ENTRYPOINT [ "java", "-cp", "app:app/lib/*", "com.willmear.taskmanager.TaskmanagerApplication"]
