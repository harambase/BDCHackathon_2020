package com.harambase.hackathon.common.support.util;

public class IDUtil {

    public static final String ROOT = "9201701000";

    public static String genUserID(String info) {
        Integer last = (int) (Math.random() * (999 - 100 + 1) + 100);
        return "9" + info.split("-")[0] + info.split("-")[1] + last;
    }

    public static String genCRN(String info) {
        Integer last = (int) (Math.random() * (99 - 10 + 1) + 10);
        return "1" + info.split("-")[0] + info.split("-")[1] + last;
    }

    public static String genTempCRN(String info) {
        Integer last = (int) (Math.random() * (99 - 10 + 1) + 10);
        return "8" + info.split("-")[0] + info.split("-")[1] + last;
    }

    public static String genTempUserID(String info) {
        Integer last = (int) (Math.random() * (999 - 100 + 1) + 100);
        return "8" + info.split("-")[0] + info.split("-")[1] + last;
    }

    public static String genContractID(String initDate) {
        Integer last = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        return initDate.split("-")[0] + initDate.split("-")[1] + initDate.split("-")[2] + last;
    }
}
