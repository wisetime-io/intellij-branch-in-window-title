import org.jetbrains.intellij.tasks.RunPluginVerifierTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.EnumSet

plugins {
  java
  kotlin("jvm") version "1.9.23"
  id("org.jetbrains.intellij") version "1.17.2"
}

group = "org.jetbrains"

version = "2.0.0"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
  compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
}

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.7.2"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  testImplementation("org.mockito:mockito-core:2.21.0")
  testImplementation("org.assertj:assertj-core:3.24.0")
}

intellij {
  // IntelliJ IDEA releases: https://www.jetbrains.com/intellij-repository/releases e.g. IC-2019.3
  // and see https://plugins.jetbrains.com/docs/intellij/build-number-ranges.html#platformVersions
  version.set("2021.2.3")
  pluginName.set("branch-window-title")
  downloadSources.set(true)
  updateSinceUntilBuild.set(false)
  // name defined in e.g. plugins/vcs-git/lib/vcs-git/META-INF/plugin.xml
  plugins.set(listOf("Git4Idea", "Subversion"))
}

tasks.withType(JavaCompile::class.java) {
  options.isDeprecation = true
  options.encoding = "UTF-8"
}

tasks {
  test { useJUnitPlatform() }

  runPluginVerifier {
    // Test oldest supported, and latest
    ideVersions.set(listOf("IC-2021.2.3", "IC-2024.1"))
    failureLevel.set(
      EnumSet.complementOf(
        EnumSet.of(
          // these are the only issues we tolerate
          RunPluginVerifierTask.FailureLevel.DEPRECATED_API_USAGES,
          RunPluginVerifierTask.FailureLevel.NOT_DYNAMIC,
        )
      )
    )
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("212")
  }
}

