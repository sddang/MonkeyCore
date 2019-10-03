package com.monkey.services.documentation;

import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.TaskStatus;


public class DocumentationEvent {

    public static void addEvent(final String className, final String description, final String screenshotFileName, final TaskStatus status,
                                final DocumentationType docType) {
        final DocumentObject taskDocument = new DocumentObject(description, screenshotFileName, status, docType);
        ExecutionManager.getMonkeyDriver().addTaskDocument(taskDocument);
    }

}
