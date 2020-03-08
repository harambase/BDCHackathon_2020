package com.harambase.hackathon.common.support.util;

/**
 * Created by Administrator on 2017/4/1.
 */
public class PageUtil {

    public static int getcPg(String currentPage) {
        int cpg = getIntegerPars(currentPage);
        if (cpg <= 0) {
            cpg = 1;
        }
        return cpg;
    }

    public static int getLimit(String limitStr) {
        int limit = getIntegerPars(limitStr);
        if (limit <= 0) {
            limit = 10;
        }
        return limit;
    }

    public static int getIntegerPars(String param) {
        if (null != param) {
            try {
                return Integer.valueOf(param);
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

}