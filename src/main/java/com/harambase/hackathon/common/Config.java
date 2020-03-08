package com.harambase.hackathon.common;

import java.util.ResourceBundle;

public class Config {

    public static String serverPath = "";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("server");
    public final static String TEMP_FILE_PATH = resourceBundle.getString("tempFile.path");

    public final static String FTP_PATH = resourceBundle.getString("ftp.path");
    public final static String FTP_SERVER = resourceBundle.getString("ftp.server");
    public final static String FTP_USERNAME = resourceBundle.getString("ftp.user");
    public final static String FTP_PASSWORD = resourceBundle.getString("ftp.password");

    static {
        try {
            serverPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
