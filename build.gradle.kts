import org.jetbrains.changelog.closure
import org.jetbrains.changelog.date

group = "be.jbeckers"
version = "1.0.4"

repositories {
    mavenCentral()
}

plugins {
    id("org.jetbrains.intellij") version "0.5.0"
    id("org.jetbrains.changelog") version "0.5.0"
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
}

intellij {
    version = "LATEST-EAP-SNAPSHOT"
}

tasks {
    patchPluginXml {
        pluginId("be.jbeckers.compare_tab_with_editor2")
        pluginDescription("Compare the currently open file to another one, by right click on editor tab.")
        changeNotes(closure { changelog.get("${project.version}").toHTML() })
        version("${project.version}")
        sinceBuild("192")
    }

    publishPlugin {
        findProperty("pluginsRepoToken")?.let { token(it) }
    }
}

changelog {
    path = "${project.projectDir}/docs/CHANGELOG.md"
}
