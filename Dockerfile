# Используем образ с Java 21
FROM openjdk:21

# Копируем файл .jar в контейнер
COPY target/offliner-0.0.1-SNAPSHOT.jar /app/offliner.jar

# Указываем рабочую директорию
WORKDIR /app
git init

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "offliner.jar"]
