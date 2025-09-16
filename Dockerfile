# Dockerfile para Main Service (Config Server + Eureka)
# Microsserviço principal que orquestra os demais

FROM eclipse-temurin:21-jdk-alpine

# Informações do mantenedor
LABEL maintainer="DevOps Team"
LABEL description="Main Service - Config Server + Eureka Server"

# Criar diretório de trabalho
WORKDIR /app

# Copiar arquivos Maven wrapper e pom.xml primeiro (para cache de dependências)
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Dar permissão de execução ao Maven wrapper
RUN chmod +x ./mvnw

# Baixar dependências (esta camada será cacheada se o pom.xml não mudar)
RUN ./mvnw dependency:go-offline -B

# Copiar código fonte
COPY src ./src

# Compilar a aplicação
RUN ./mvnw clean package -DskipTests

# Expor a porta 8888 (Config Server + Eureka)
EXPOSE 8888

# Comando para executar a aplicação
CMD ["java", "-jar", "target/main-service-0.0.1-SNAPSHOT.jar"]
