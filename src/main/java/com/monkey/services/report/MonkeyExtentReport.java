package com.monkey.services.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.List;

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
