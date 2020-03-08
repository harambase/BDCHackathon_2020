package com.harambase.hackathon.common.support.util;

import com.harambase.hackathon.common.ResultMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {

    public static ResultMap sendRestRequest(String url, HttpMethod method, Object body) {

        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("userId", SessionUtil.getUserId());
        httpHeaders.set("Content-UserTypeConst", "application/json;charset=UTF-8");
        HttpEntity httpEntity = new HttpEntity<>(body, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResultMap> responseMessage = restTemplate.exchange(url, method, httpEntity, ResultMap.class);

        return responseMessage.getBody();
    }
}
