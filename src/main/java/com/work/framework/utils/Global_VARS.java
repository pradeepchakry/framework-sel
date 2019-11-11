package com.work.framework.utils;

import java.io.File;

/**
 * Global Variables Definition Class
 */
public class Global_VARS {
    // target app defaults
    public static final String BROWSER = "firefox";
    public static final String PLATFORM = "linux";
    public static final String ENVIRONMENT = "local";
    public static final String DEF_BROWSER = "null";
    public static final String DEF_PLATFORM = "null";
    public static final String DEF_ENVIRONMENT = "null";
    public static final String PROPS_PATH = "null";
    //test output path defaults
    public static final String TEST_OUTPUT_PATH = "testOutput/";
    public static final String LOGFILE_PATH = TEST_OUTPUT_PATH + "Logs/";
    //timeout defaults
    public static final int TIMEOUT_MINUTE = 60;
    public static final int TIMEOUT_SECOND = 1;
    public static final int TIMEOUT_ZERO = 0;
    public static final int TIMEOUT_ELEMENT = 20;
    //driver class defaults
    public static String propFile = "src/main/resources/selenium.props";
    public static final String SE_PROPS = new File(propFile).getAbsolutePath();
}
