package be.jbeckers.ctwe

import com.intellij.openapi.components.ComponentManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

/**
 * @author jbecke4
 * @since 23-9-2019
 */
class CompareTabWithEditorService private constructor(project: Project) {

    @Volatile
    var currentFile: VirtualFile? = null
        private set

    init {
        project.messageBus.connect().subscribe(
            FileEditorManagerListener.FILE_EDITOR_MANAGER,
            object : FileEditorManagerListener {
                override fun selectionChanged(event: FileEditorManagerEvent) {
                    this@CompareTabWithEditorService.currentFile = event.newFile
                }
            }
        )
    }

    companion object {

        fun getInstance(project: Project): CompareTabWithEditorService {
            return project.getService(CompareTabWithEditorService::class.java)
        }
    }
}
