import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.PublishTask

group = "be.jbeckers"
version = "1.0.3"

repositories {
    mavenCentral()
}

plugins {
    id("org.jetbrains.intellij") version "0.4.21"
    kotlin("jvm") version "1.3.72"
}

intellij {
    version = "LATEST-EAP-SNAPSHOT"
}

tasks {
    getByName<PatchPluginXmlTask>("patchPluginXml") {
        pluginId("be.jbeckers.compare_tab_with_editor2")
        pluginDescription("Allows to compare file selected by right click on tab with currently edited file.")
        changeNotes("""
                    1.0.3 Support the latest 2020.2 EAP
                    """.trimIndent())
        version("1.0.3")
        sinceBuild("192")
    }

    getByName<PublishTask>("publishPlugin") {
        findProperty("pluginsRepoToken")?.let { token(it) }
    }
}
