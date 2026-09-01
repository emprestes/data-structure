plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

val javafxVersion = "23.0.2"
val os = org.gradle.internal.os.OperatingSystem.current()
val arch = System.getProperty("os.arch")
val platform = when {
    os.isMacOsX -> if (arch == "aarch64") "mac-aarch64" else "mac"
    os.isWindows -> if (arch.contains("64")) "win" else "win-x86"
    else -> if (arch == "aarch64") "linux-aarch64" else "linux"
}

dependencies {
    implementation(project(":core"))

    listOf("base", "graphics", "controls").forEach { mod ->
        implementation("org.openjfx:javafx-$mod:$javafxVersion:$platform")
        runtimeOnly("org.openjfx:javafx-$mod:$javafxVersion:$platform")
    }

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

javafx {
    version = javafxVersion
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.base")
}

application {
    mainClass = "emprestes.ds.app.StackVisualizerApp"
    applicationDefaultJvmArgs = listOf("--add-modules=javafx.controls,javafx.graphics,javafx.base")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}

// Keeps the JavaFX module path correct when IntelliJ delegates execution to Gradle.
tasks.named<JavaExec>("run") {
    val javafxJars = configurations.runtimeClasspath.get().filter { it.name.startsWith("javafx") }
    jvmArgs = listOf(
        "--module-path", javafxJars.asPath,
        "--add-modules", "javafx.controls,javafx.graphics,javafx.base"
    )
}
