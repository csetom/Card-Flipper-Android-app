buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.15.1")
    }
}

plugins {
    kotlin("android") version "1.6.10" apply false
    kotlin("kapt") version "1.6.20" apply false
    id("com.android.application") version "8.1.4" apply false
    id("com.android.library") version "7.1.2" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
