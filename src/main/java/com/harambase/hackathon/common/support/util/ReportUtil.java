package com.harambase.hackathon.common.support.util;

import com.harambase.hackathon.common.constant.GradeConst;
import org.apache.commons.lang3.StringUtils;

public class ReportUtil {

    public static String infoConverter(String info) {
        String[] infoArrays = info.split("-");
        String retInfo = "";
        switch (infoArrays[1]) {
            case "01":
                retInfo = "春季(Spring)";
                break;
            case "02":
                retInfo = "秋季(Fall)";
                break;
            case "03":
                retInfo = "夏季(Summer)";
                break;
        }
        return retInfo + ", " + infoArrays[0];
    }

    public static int qualityPointsCalculator(int credits, String grade) {

        if (grade.equals("*"))
            return 0;

        if (StringUtils.isAlpha(grade)) {
            if (grade.toUpperCase().equals("A-") || grade.toUpperCase().equals("B+"))
                grade = "AB";
            if (grade.toUpperCase().equals("B-") || grade.toUpperCase().equals("C+"))
                grade = "BC";
            if (grade.toUpperCase().equals("C-") || grade.toUpperCase().equals("D+"))
                grade = "CD";

            GradeConst point = GradeConst.valueOf(grade.toUpperCase());
            double ret = credits * point.getPoint();
            return (int) ret;
        }
        if (StringUtils.isNumeric(grade)) {
            Double g = Double.parseDouble(grade);

            if (g >= 93.0)
                grade = "A";
            else if (g >= 89.0)
                grade = "AB";
            else if (g >= 83)
                grade = "B";
            else if (g >= 79)
                grade = "BC";
            else if (g >= 73)
                grade = "C";
            else if (g >= 69)
                grade = "CD";
            else if (g >= 60)
                grade = "D";
            else
                grade = "F";

            GradeConst point = GradeConst.valueOf(grade.toUpperCase());
            return Integer.getInteger(String.valueOf(credits * point.getPoint()));
        }

        return 0;
    }
}
