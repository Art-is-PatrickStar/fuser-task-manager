package com.wsw.fusertaskmanager.controller;

import com.wsw.fusertaskmanager.service.TesterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author WangSongWen
 * @Date: Created in 13:35 2020/11/20
 * @Description:
 */
@RestController
public class TesterController {
    @Resource
    private TesterService testerService;

    @PostMapping("/tester/create")
    public int create(@RequestParam("name") String name, @RequestParam("remark") String remark){
        return testerService.insert(name, remark);
    }
}
