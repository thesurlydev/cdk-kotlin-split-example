import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
    id("com.github.johnrengelman.shadow") version "4.0.3"
}

group = "io.futz"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

allprojects {
    apply {
        plugin("kotlin")
    }

    dependencies {
        compile(kotlin("stdlib-jdk8"))
        testImplementation("junit:junit:4.12")
    }
}

subprojects {
    apply {
        plugin("com.github.johnrengelman.shadow")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    repositories {
        jcenter()
        mavenCentral()
    }
}


project(":function") {
    // nothing required here
}

project(":function-stack") {
    dependencies {
        val cdkVersion = "0.20.0"
        compile("software.amazon.awscdk:cdk:$cdkVersion")
        implementation("software.amazon.awscdk:iam:$cdkVersion")
        implementation("software.amazon.awscdk:lambda:$cdkVersion")
        implementation("software.amazon.awscdk:stepfunctions:$cdkVersion")
    }
}

project(":provision") {
    dependencies {
        api(project(":function-stack"))
    }

    tasks.withType<ShadowJar> {
        baseName = "app"
        classifier = ""
        version = ""
    }

    tasks.withType<Jar> {
        manifest {
            attributes(mapOf(
                "Main-Class" to "io.futz.MyAppKt",
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            ))
        }
    }
}
