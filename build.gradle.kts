group = "be.jbeckers"
version = "1.0.2"

repositories {
    mavenCentral()
}

plugins {
    id("org.jetbrains.intellij") version "0.4.15"
    kotlin("jvm") version "1.3.61"
}

intellij {
    version = "LATEST-EAP-SNAPSHOT"
}

/**
 * Simple function to load HTML files and remove the surrounding `<html>` tags. This is useful for maintaining changes-notes
 * and the description of plugins in separate HTML files which makes them much more readable.
 */
fun htmlFixer(filename: String): String {
    if (!File(filename).exists()) {
        logger.error("File $filename not found.")
    } else {
        return File(filename).readText().replace("<html>", "").replace("</html>", "")
    }
    return ""
}

tasks {
    named<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
        pluginId("be.jbeckers.compare_tab_with_editor2")
        pluginDescription(htmlFixer("src/main/resources/META-INF/description.html"))
        changeNotes(htmlFixer("src/main/resources/META-INF/change-notes.html"))
        version("1.0.2")
        sinceBuild("192")
    }

    named<org.jetbrains.intellij.tasks.PublishTask>("publishPlugin") {
        findProperty("pluginsRepoToken")?.let { token(it) }
    }
}