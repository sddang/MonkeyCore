package com.monkey.services.report;

import java.util.List;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class MonkeyExtentReport extends ExtentReports {

    public MonkeyExtentReport(final String filePath) {
        super(filePath);
    }

    public MonkeyExtentReport(final String name, final boolean b) {
        super(name, b);
    }

    public List<ExtentTest> getReportTestList() {
        return this.getTestList();
    }

}
