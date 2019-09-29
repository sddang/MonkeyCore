

package com.monkey.services.documentation;

import com.monkey.core.task.TaskStatus;


public class DocumentObject {

    private String description;
    private String screenShotPath;
    private TaskStatus status;
    private DocumentationType docType;

    public DocumentObject(final String description, final String screenShotPath, final TaskStatus status, final DocumentationType docType) {
        this.description = description;
        this.screenShotPath = screenShotPath;
        this.status = status;
        this.docType = docType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the screenShotPath
     */
    public String getScreenShotPath() {
        return this.screenShotPath;
    }

    /**
     * @param screenShotPath the screenShotPath to set
     */
    public void setScreenShotPath(final String screenShotPath) {
        this.screenShotPath = screenShotPath;
    }

    /**
     * @return the status
     */
    public TaskStatus getStatus() {
        return this.status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final TaskStatus status) {
        this.status = status;
    }

    public DocumentationType getDocType() {
        return this.docType;
    }

    public void setDocType(final DocumentationType docType) {
        this.docType = docType;
    }
}
