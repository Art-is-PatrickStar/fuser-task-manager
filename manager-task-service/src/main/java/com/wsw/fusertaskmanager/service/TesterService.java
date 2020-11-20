package com.wsw.fusertaskmanager.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WangSongWen
 * @Date: Created in 13:52 2020/11/20
 * @Description: 调用tester服务
 */
@FeignClient(value = "manager-tester-service")
public interface TesterService {

    @PostMapping("/tester/create")
    int create(@RequestParam("taskId") Long taskId, @RequestParam("taskName") String taskName, @RequestParam("name") String name, @RequestParam("remark") String remark);
}
