# 1. Use an official JDK image as the base image
FROM openjdk:21-jdk

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the JAR file into the container
COPY target/EComBookStore.jar EComBookStore.jar

# 4. Expose the port (optional but recommended)
EXPOSE 8080

# 5. Command to run the application
ENTRYPOINT ["java", "-jar", "EComBookStore.jar"]
