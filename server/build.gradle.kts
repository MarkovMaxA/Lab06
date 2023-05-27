plugins {
    id("java")
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
}

group = "org.example"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
