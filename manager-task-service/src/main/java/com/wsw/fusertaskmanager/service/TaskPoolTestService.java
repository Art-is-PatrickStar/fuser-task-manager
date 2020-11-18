package com.wsw.fusertaskmanager.service;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @Author WangSongWen
 * @Date: Created in 17:40 2020/11/18
 * @Description:
 */
@Service
public class TaskPoolTestService {
    String getTaskPoolListURL = "http://localhost:1998/manager-task-service/task/dosth";
    RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getTaskPoolData(){
        Map<String, Object> taskPoolMap = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1aWRcIjoxLFwidXNlcm5hbWVcIjpcIndzd1wiLFwidXNlcm5hbWVDSE5cIjpcIueOi-advuaWh1wifSJ9.x91ftNfBWLzRvDFlXw4lz4OrKJtX2QSmKa45zvhftdE");
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(getTaskPoolListURL, String.class, headers);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
        if ((response != null ? response.getStatusCodeValue() : 0) == HttpStatus.OK.value()){
            Map<String, Object> responseBodyMap = JSON.parseObject(response != null ? response.getBody() : null, Map.class);
            Integer code = MapUtil.getInt(responseBodyMap, "code");
            if (code == 200 && !MapUtil.isEmpty(responseBodyMap)){
                String data = MapUtil.getStr(responseBodyMap, "data");
                if (null != data){
                    taskPoolMap.put("data", data);
                }
            }else {
                String message = MapUtil.getStr(responseBodyMap, "message");
                taskPoolMap.put("code", code);
                taskPoolMap.put("message", message);
            }
        }

        return taskPoolMap;
    }

}
