plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // https://mvnrepository.com/artifact/io.github.selevinia/selevinia-spring-boot-autoconfigure-tarantool
    implementation("io.github.selevinia:selevinia-spring-boot-autoconfigure-tarantool:0.3.2")
    // https://mvnrepository.com/artifact/org.tarantool/connector
    implementation("org.tarantool:connector:1.9.4")
    implementation("org.apache.kafka:kafka-clients:3.6.1")
    // https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka
    implementation("org.springframework.kafka:spring-kafka:3.1.2")
    implementation("org.apache.kafka:kafka-streams")
    // https://mvnrepository.com/artifact/io.github.selevinia/spring-data-tarantool
    implementation("io.github.selevinia:spring-data-tarantool:0.3.2")
    // https://mvnrepository.com/artifact/io.tarantool/spring-data-tarantool
    implementation("io.tarantool:spring-data-tarantool:0.6.1")








}

tasks.withType<Test> {
    useJUnitPlatform()
}
