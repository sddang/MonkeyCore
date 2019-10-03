package com.monkey.impl.drivers;

import com.monkey.core.driver.MonkeyDriver;
import com.monkey.services.data.DataMapper;
import com.monkey.services.documentation.DocumentObject;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MonkeySeleniumDriver extends RemoteWebDriver implements MonkeyDriver {

    private final List<DocumentObject> taskDocumentationList = new ArrayList<DocumentObject>();
    private DataMapper dataMapper;
    private String testFileName;
    private String language;
    private ExtentTest testReport;

    public MonkeySeleniumDriver(final URL remoteAddress, final Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    public DataMapper getDataMapper() {
        return this.dataMapper;
    }

    public void setDataMapper(final DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public String getTestFileName() {
        return this.testFileName;
    }

    public void setTestFileName(final String testFileName) {
        this.testFileName = testFileName;
    }

    public void addTaskDocument(final DocumentObject taskDocument) {
        this.taskDocumentationList.add(taskDocument);
    }

    public List<DocumentObject> getTaskDocumentationList() {
        return this.taskDocumentationList;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public ExtentTest getTestReport() {
        return this.testReport;
    }

    public void setTestReport(final ExtentTest test) {
        testReport = test;
    }
}