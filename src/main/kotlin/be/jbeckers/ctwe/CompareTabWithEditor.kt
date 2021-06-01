package be.jbeckers.ctwe

import com.intellij.diff.DiffContentFactory
import com.intellij.diff.actions.CompareFilesAction
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diff.DiffBundle
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile

class CompareTabWithEditor : CompareFilesAction() {

    override fun getDiffRequest(e: AnActionEvent): DiffRequest? {
        return if (super.getDiffRequest(e) != null) {
            super.getDiffRequest(e)
        } else {
            val project = e.project ?: return null

            val left = FileEditorManagerEx.getInstanceEx(project).currentFile
            val right = CompareTabWithEditorService.getInstance(project).currentFile
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
            val left = FileEditorManagerEx.getInstanceEx(project).currentFile
            val right = CompareTabWithEditorService.getInstance(project).currentFile
            presentation.isVisible = left != null && right != null && left != right
        }
    }

    companion object {

        protected fun getVirtualFileContentTitle(documentFile: VirtualFile): String {
            val name = documentFile.name
            val parent = documentFile.parent
            return if (parent != null) {
                name + " (" + FileUtil.toSystemDependentName(parent.path) + ")"
            } else name
        }
    }
}
