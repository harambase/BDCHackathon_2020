package com.harambase.hackathon.common.support.util;

import org.apache.commons.lang3.StringUtils;

public class CollectionUtil {

    public static <T> String join(T[] array, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    public static Integer[] toIntArray(String split, String str) {
        if (StringUtils.isEmpty(str)) {
            return new Integer[]{};
        }
        String[] arr = str.split(split);
        Integer[] ints = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Integer v = Integer.parseInt(arr[i]);
            ints[i] = v;
        }
        return ints;
    }
}
