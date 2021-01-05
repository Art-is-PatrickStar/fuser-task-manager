package com.wsw.fusertaskmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WangSongWen
 * @Date: Created in 13:41 2021/1/5
 * @Description: 调用recepienter服务
 */
@FeignClient(value = "manager-recepienter-service")
public interface RecepienterClient {

    @PostMapping("/recepienter/create")
    int create(@RequestParam("taskId") Long taskId, @RequestParam("taskName") String taskName, @RequestParam("name") String name, @RequestParam("remark") String remark);

}
