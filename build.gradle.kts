plugins {
    application
    java
}

group = "koh.service"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("koh.service.mail.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.slf4j:slf4j-reload4j:2.0.13")

    implementation("org.apache.kafka:kafka-clients:3.7.1")

    implementation("com.fasterxml.jackson.core:jackson-core:2.15.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")

    implementation("com.sun.mail:jakarta.mail:2.0.1")

    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
