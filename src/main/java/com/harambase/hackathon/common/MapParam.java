package com.harambase.hackathon.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MapParam extends HashMap<String, Object> implements Serializable {

    public static Map<String, String> pieChartValue(String value, String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("value", value);
        map.put("name", name);
        return map;
    }

}