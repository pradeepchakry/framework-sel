package com.work.framework.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNG_Listener extends TestListenerAdapter {

    private String logFile = getLogFileName();

    private static Logger logger = LoggerFactory.getLogger(TestNG_Listener.class);

    @Override
    public void onStart(ITestContext testContext) {
        try {
            log("\n Suite Start Date: " +
                    new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss")
            .format(new Date()));
        } catch (Exception e) {}

        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        try {
            log("\n Total Passed = " +
                    getPassedTests().size() +
                    ", Total Failed = " +
                    getFailedTests().size() +
                    ", Total Skipped = " +
                    getSkippedTests().size() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onFinish(testContext);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        try {
            log("***Result = PASSED\n");
            log(testResult.getEndMillis(),
                    "TEST -> " +
                    testResult.getInstanceName() +
                    "." + testResult.getName());
            log("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onTestSuccess(testResult);
    }

    public void log(long date, String dataLine) throws Exception {
        System.out.format("%s%n", String.valueOf(new Date(date)), dataLine);

        if (logFile != null) {
            writeLogFile(logFile, dataLine);
        }
    }

    public void log(String dataLine) throws Exception {
        System.out.format("%s%n", dataLine);

        if (logFile != null) {
            writeLogFile(logFile, dataLine);
        }
    }

    public String getLogFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if(logFile == null) {
            this.logFile = new File
                    ("Test_" + sdf.format(timestamp.getTime()) + ".log").getAbsolutePath();
        }
        logger.info("log file is located at --> " + logFile);
        return this.logFile;
    }

    public void writeLogFile(String file, String dataLine) {
        try {
            FileWriter writer = new FileWriter(new File(file));
            writer.append(dataLine);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
