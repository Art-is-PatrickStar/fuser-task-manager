package com.wsw.fusertaskmanager;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author WangSongWen
 * @Date: Created in 17:40 2020/11/18
 * @Description:
 */
@Service
public class TaskPoolTestService {
    String getTaskPoolListURL = "http://localhost:1998/manager-task-service/task/dosth";
    String postTaskPoolListURL = "http://localhost:1998/manager-auth-service/auth";
    RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getTaskPoolData(){
        Map<String, Object> taskPoolMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1aWRcIjoxLFwidXNlcm5hbWVcIjpcIndzd1wiLFwidXNlcm5hbWVDSE5cIjpcIueOi-advuaWh1wifSJ9.hFGpE0rbrA9T1MwrqynJiaJQgL-fCN9N4vJG43eTbCA");
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(getTaskPoolListURL, HttpMethod.GET, request, String.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }

        if (null != response && response.getStatusCodeValue() == HttpStatus.OK.value()) {
            Map<String, Object> responseBodyMap = JSON.parseObject(response.getBody(), Map.class);
            if (MapUtils.isNotEmpty(responseBodyMap)) {
                Object code = responseBodyMap.get("code");
                Object data = responseBodyMap.get("data");
                Object message = MapUtils.getString(responseBodyMap, "message");
                if (null != code && null != data && null != message) {
                    taskPoolMap.put("code", code);
                    taskPoolMap.put("data", data);
                    taskPoolMap.put("message", message);
                }
            }
        }
        return taskPoolMap;
    }

    public List<Map<String, Object>> postTaskPoolData(String username, String password){
        List<Map<String, Object>> taskPoolList = new ArrayList<>();
        // 解决返回结果中文乱码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.put("username", Collections.singletonList(username));
        body.put("password", Collections.singletonList(password));

        HttpEntity<MultiValueMap> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.postForEntity(postTaskPoolListURL, request, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (null != response && response.getStatusCodeValue() == HttpStatus.OK.value()) {
            Map<String, Object> responseBodyMap = JSON.parseObject(response.getBody(), Map.class);
            if (responseBodyMap != null) {
                Object code = responseBodyMap.get("code");
                if (code.equals(200)){
                    Map<String,Object> dataMap = (Map<String, Object>) responseBodyMap.get("data");
                    if (null != dataMap){
                        Map<String, Object> userMap = (Map<String, Object>) dataMap.get("user");
                        if (null != userMap){
                            Map<String, Object> userDataMap = new HashMap<>();
                            userDataMap.put("userId", userMap.get("uid"));
                            userDataMap.put("username", userMap.get("username"));
                            userDataMap.put("password", userMap.get("password"));
                            userDataMap.put("usernameCHN", userMap.get("usernameCHN"));
                            taskPoolList.add(userDataMap);
                        }
                        Object token = dataMap.get("token");
                        if (null != token){
                            Map<String, Object> tokenDataMap = new HashMap<>();
                            tokenDataMap.put("token", token);
                            taskPoolList.add(tokenDataMap);
                        }
                    }
                }
            }
        }

        return taskPoolList;
    }

}
