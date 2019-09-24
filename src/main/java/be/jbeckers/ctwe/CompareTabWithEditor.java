//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.jbeckers.ctwe;

import com.intellij.diff.DiffContentFactory;
import com.intellij.diff.actions.CompareFilesAction;
import com.intellij.diff.requests.DiffRequest;
import com.intellij.diff.requests.SimpleDiffRequest;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.diff.DiffBundle;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompareTabWithEditor extends CompareFilesAction {
    public CompareTabWithEditor() {
    }

    @Nullable
    @Override
    protected DiffRequest getDiffRequest(@NotNull AnActionEvent e) {
        DiffRequest diffRequest = super.getDiffRequest(e);
        if (diffRequest != null) {
            return diffRequest;
        }

        Project project = e.getProject();
        if (project == null) {
            return null;
        }

        VirtualFile left = FileEditorManagerEx.getInstanceEx(project).getCurrentFile();
        VirtualFile right = CompareTabWithEditorService.getInstance(project).getCurrentFile();
        if (left == null || right == null) {
            return null;
        }

        return new SimpleDiffRequest(
                DiffBundle.message("diff.element.qualified.name.vs.element.qualified.name.dialog.title", getVirtualFileContentTitle(left), getVirtualFileContentTitle(right)),
                DiffContentFactory.getInstance().create(project, left),
                DiffContentFactory.getInstance().create(project, right),
                getVirtualFileContentTitle(left),
                getVirtualFileContentTitle(right));
    }

    @NotNull
    protected static String getVirtualFileContentTitle(@NotNull final VirtualFile documentFile) {
        String name = documentFile.getName();
        VirtualFile parent = documentFile.getParent();
        if (parent != null) {
            return name + " (" + FileUtil.toSystemDependentName(parent.getPath()) + ")";
        }
        return name;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        Presentation presentation = e.getPresentation();
        if (project == null) {
            presentation.setVisible(false);
        } else {
            VirtualFile left = FileEditorManagerEx.getInstanceEx(project).getCurrentFile();
            VirtualFile right = CompareTabWithEditorService.getInstance(project).getCurrentFile();
            if (left == null || right == null || left.equals(right)) {
                presentation.setVisible(false);
            } else {
                presentation.setVisible(true);
            }
        }

    }
}
