

/**
 * This package encapsulate all the monkey services
 */
package com.monkey.services.documentation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.ITestNGMethod;

import com.monkey.api.MonkeyLogger;
import com.monkey.api.enumeration.LogLevel;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.TaskStatus;
import com.monkey.core.utils.Utils;
import com.monkey.services.ScreenShotTaker;


public class DocumentationGenerator {

    public static final String FUNCTIONAL_DOC_PATH = "target/documentationFunc";
    public static final String TECHNICAL_DOC_PATH = "target/documentationTech";
    public static final String PROJECT_RELATIVE_PATH = "../../";

    /**
     * Is the main class that generate the documentation of each test
     *
     * @param method
     * @throws IOException
     */
    public static void generateDocumentation(final ITestNGMethod method) throws IOException {
        String path = method.getTestClass().getName() + "." + method.getMethodName();
        path = StringUtils.replace(path, "\\.", "_");
        if (ExecutionManager.getConfiguration().isGenerateTechnicalDoc()) {
            Utils.createFolderIfNotExist(DocumentationGenerator.TECHNICAL_DOC_PATH);
            final String techDocPath = DocumentationGenerator.TECHNICAL_DOC_PATH + "/" + path + ".html";
            DocumentationGenerator.generateDocumentation(techDocPath, DocumentationType.TECHNICAL);
        }
        if (ExecutionManager.getConfiguration().isGenerateFunctionalDoc()) {
            Utils.createFolderIfNotExist(DocumentationGenerator.FUNCTIONAL_DOC_PATH);
            final String funcDocPath = DocumentationGenerator.FUNCTIONAL_DOC_PATH + "/" + path + ".html";
            DocumentationGenerator.generateDocumentation(funcDocPath, DocumentationType.FUNCTIONAL);
        }
    }

    private static void generateDocumentation(final String pathDocFile, final DocumentationType docType) throws IOException {

        try {
            final File testDocumentationFile = Utils.createFile(pathDocFile);
            final BufferedWriter output = new BufferedWriter(new FileWriter(testDocumentationFile));
            output.write(DocumentationGenerator.getHeader());
            output.write(DocumentationGenerator.getBody(docType));
            output.write(DocumentationGenerator.getFooter());
            output.close();
        } catch (final IOException e) {
            MonkeyLogger.log(ScreenShotTaker.class.getName(), "Error : Pb happen when generating documentation!!!\n" + e.getMessage(), LogLevel.FATAL);
            throw e;
        }
    }

    /**
     * Return the header of the file
     *
     * @return
     */
    private static String getHeader() {
        final StringBuffer header = new StringBuffer(500);
        header.append("<html><head><title>").append("Test Page documentation </title>").append(
                "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'></head>");
        return header.toString();
    }

    /**
     * return the footer of the file
     *
     * @return
     */
    private static String getFooter() {
        final StringBuffer footer = new StringBuffer(500);
        footer.append("</body></html>");
        return footer.toString();
    }

    /**
     * return the body of the file
     *
     * @return
     */
    private static String getBody(final DocumentationType docType) {
        final StringBuffer body = new StringBuffer(1000);
        body.append("<body>");
        body.append("<div class='jumbotron' style='margin-left:15px'> <h1>").append("Documentation for Test :")
                .append("</h1>");
        body.append("<p>").append(ExecutionManager.getMonkeyDriver().getTestFileName()).append("</p></div>");

        body.append("<div style='margin-left:15px'><table class='table table-hover  table-bordered'>").append(
                "<tr class='warning'><th >ID</th><th>Description</th> <th>ScreenShot</th> <th>Status</th></tr>");
        final List<DocumentObject> docs = ExecutionManager.getMonkeyDriver().getTaskDocumentationList();
        int i = 0;
        for (final DocumentObject doc : docs) {
            if (doc.getDocType().equals(DocumentationType.ALL) || doc.getDocType().equals(docType)) {
                final boolean isFailedTask = doc.getStatus().equals(TaskStatus.FAILED);
                body.append("<tr").append(isFailedTask ? " class='danger' >" : " class='info'>");
                body.append("<td>").append(isFailedTask ? "*" : i).append("</td><td>").append(doc.getDescription())
                        .append("</td>");
                body.append("<td>");
                if (!doc.getScreenShotPath().equals("")) {
                    body.append("<img src=\"").append(DocumentationGenerator.PROJECT_RELATIVE_PATH + doc.getScreenShotPath())
                            .append("\" width=\"500\">");
                }
                body.append("</td>");
                if (isFailedTask) {
                    body.append(
                            "<td class='danger' ><button type='button' class='btn btn-danger'>Failed</button></td> </tr>");
                } else {
                    body.append(
                            "<td class='success' ><button type='button' class='btn btn-success'>Passed</button></td> </tr>");
                }

                i++;
            }
        }
        return body.toString();
    }

}
