package be.jbeckers.ctwe

import com.intellij.diff.DiffContentFactory
import com.intellij.diff.actions.CompareFilesAction
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.diff.DiffBundle
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile

class CompareTabWithEditorAction : CompareFilesAction() {

    @Suppress("DialogTitleCapitalization")
    override fun getDiffRequest(e: AnActionEvent): DiffRequest? {
        return if (super.getDiffRequest(e) != null) {
            super.getDiffRequest(e)
        } else {
            val project = e.project ?: return null

            val left = e.getData(VIRTUAL_FILE)
            val right = project.getService(CompareTabWithEditorService::class.java).currentFile
            if (left == null || right == null) {
                null
            } else {
                SimpleDiffRequest(
                    DiffBundle.message(
                        "diff.element.qualified.name.vs.element.qualified.name.dialog.title",
                        getVirtualFileContentTitle(left),
                        getVirtualFileContentTitle(right)
                    ),
                    DiffContentFactory.getInstance().create(project, left),
                    DiffContentFactory.getInstance().create(project, right),
                    getVirtualFileContentTitle(left),
                    getVirtualFileContentTitle(right)
                )
            }
        }
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val presentation = e.presentation
        if (project == null) {
            presentation.isVisible = false
        } else {
            val left = e.getData(VIRTUAL_FILE)
            val right = project.getService(CompareTabWithEditorService::class.java).currentFile
            presentation.isVisible = left != null && right != null && left != right
        }
    }

    private fun getVirtualFileContentTitle(documentFile: VirtualFile): String {
        val name = documentFile.name
        val parent = documentFile.parent
        return if (parent != null) {
            name + " (" + FileUtil.toSystemDependentName(parent.path) + ")"
        } else name
    }
}

class CompareTabWithEditorService {

    @Volatile
    var currentFile: VirtualFile? = null
        internal set
}

class CompareTabWithEditorListener : FileEditorManagerListener {

    private val project: Project

    constructor(project: Project) {
        this.project = project
    }

    override fun selectionChanged(event: FileEditorManagerEvent) {
        project.getService(CompareTabWithEditorService::class.java).currentFile = event.newFile
    }
}
