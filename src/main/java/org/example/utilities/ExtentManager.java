package org.example.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ExtentManager {
    private static final Logger log = LogManager.getLogger(ExtentManager.class.getName());
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static synchronized ExtentReports createInstance() {
        String fileName = Util.getReportName();
        String reportDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportDirectory).mkdirs();
        String path = reportDirectory + fileName;
        log.info("Report path: " + path);

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Run");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization", "Test");
        extent.setSystemInfo("Automation Framework", "Selenium WebDriver");
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
