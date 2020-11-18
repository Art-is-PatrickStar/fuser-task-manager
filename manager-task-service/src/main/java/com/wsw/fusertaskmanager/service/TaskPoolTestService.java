package com.wsw.fusertaskmanager.service;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 17:40 2020/11/18
 * @Description:
 */
@Service
public class TaskPoolTestService {
    private String getTaskPoolListURL = "";
    private RestTemplate restTemplate = new RestTemplate();
    private Map<String, Object> uriVariablesMap = new LinkedHashMap<>();

    public List<Map<String, Object>> getTaskPoolList(){
        List<Map<String, Object>> taskPoolList = new ArrayList<>();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(getTaskPoolListURL, String.class, uriVariablesMap);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        if (response.getStatusCodeValue() == HttpStatus.OK.value()){
            Map<String, Object> responseBodyMap = JSON.parse(response.getBody(), Map.class);

        }


        return taskPoolList;
    }

}
