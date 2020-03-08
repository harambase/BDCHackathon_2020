package com.harambase.hackathon.common.support.util;

public class BuildUrlUtil {

    public static StringBuilder buildUrl(String remotePath, String ip, int port) {
        String urlPrefix = ip + ":" + port;
        StringBuilder requestUrl = new StringBuilder(urlPrefix);
        requestUrl.append(remotePath);
        return requestUrl;
    }

}
