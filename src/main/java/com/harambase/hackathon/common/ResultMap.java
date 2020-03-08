package com.harambase.hackathon.common;

import java.util.HashMap;

public class ResultMap extends HashMap {

    public int getCode() {
        return (int) super.get("code");
    }

    public void setCode(int code) {
        super.put("code", code);
    }

    public String getMsg() {
        return (String) super.get("msg");
    }

    public void setMsg(String msg) {
        super.put("msg", msg);
    }

    public Object getData() {
        return this.get("data");
    }

    public void setData(Object data) {
        super.put("data", data);
    }
}
