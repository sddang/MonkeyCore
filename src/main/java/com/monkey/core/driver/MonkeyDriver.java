package com.monkey.core.driver;

import java.util.List;

import org.openqa.selenium.*;

import com.monkey.services.data.DataMapper;
import com.monkey.services.documentation.DocumentObject;
import com.relevantcodes.extentreports.ExtentTest;

public interface MonkeyDriver extends HasCapabilities, JavascriptExecutor, TakesScreenshot, WebDriver {

    DataMapper getDataMapper();

    void setDataMapper(DataMapper dataMapper);

    String getTestFileName();

    void setTestFileName(String testFileName);

    void addTaskDocument(DocumentObject taskDocument);

    List<DocumentObject> getTaskDocumentationList();

    String getLanguage();

    void setLanguage(String language);

    ExtentTest getTestReport();

    void setTestReport(ExtentTest testReport);

}
