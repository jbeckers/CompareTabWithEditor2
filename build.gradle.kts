import org.jetbrains.changelog.closure

group = "be.jbeckers"
version = "1.0.3"

repositories {
    mavenCentral()
}

plugins {
    id("org.jetbrains.intellij") version "0.4.21"
    id("org.jetbrains.changelog") version "0.1.5"
    kotlin("jvm") version "1.3.72"
}

intellij {
    version = "LATEST-EAP-SNAPSHOT"
}

tasks {
    patchPluginXml {
        pluginId("be.jbeckers.compare_tab_with_editor2")
        pluginDescription("Compare the currently open file to another one, by right click on editor tab.")
        changeNotes(closure { changelog.get("${project.version}").noHeader().toHTML() })
        version("${project.version}")
        sinceBuild("192")
    }

    publishPlugin {
        findProperty("pluginsRepoToken")?.let { token(it) }
    }
}

changelog {
    version = "${project.version}"
    path = "${project.projectDir}/docs/CHANGELOG.md"
    format = "[{0}]"
    keepUnreleasedSection = true
    unreleasedTerm = "Unreleased"
}
