package be.jbeckers.ctwe;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author jbecke4
 * @since 23-9-2019
 */
public class CompareTabWithEditorService {

    private volatile VirtualFile editorFile;

    public static CompareTabWithEditorService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, CompareTabWithEditorService.class);
    }

    protected CompareTabWithEditorService(@NotNull Project project) {
        project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                CompareTabWithEditorService.this.editorFile = event.getNewFile();
            }
        });
    }

    public VirtualFile getCurrentFile() {
        return this.editorFile;
    }
}
