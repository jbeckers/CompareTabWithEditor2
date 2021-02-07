import org.jetbrains.changelog.closure
import org.jetbrains.changelog.date

group = "be.jbeckers"
version = "1.0.4"

repositories {
    mavenCentral()
    maven("https://www.jetbrains.com/intellij-repository/snapshots")
}

plugins {
    id("org.jetbrains.intellij") version "0.6.5"
    id("org.jetbrains.changelog") version "1.0.1"
    id("org.jetbrains.kotlin.jvm") version "1.4.30"
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

    runPluginVerifier {
        ideVersions(listOf(
            "IC-2109.2.4", "IC-2109.3.5",
            "IC-2020.1.4", "IC-2020.2.4", "IC-2020.3.2",
            "IC-2021.1"))
    }

    publishPlugin {
        findProperty("pluginsRepoToken")?.let { token(it) }
    }
}

changelog {
    path = "${project.projectDir}/docs/CHANGELOG.md"
}
