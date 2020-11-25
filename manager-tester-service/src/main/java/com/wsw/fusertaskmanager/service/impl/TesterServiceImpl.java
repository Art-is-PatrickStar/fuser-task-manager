package com.wsw.fusertaskmanager.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.wsw.fusertaskmanager.mapper.TesterMapper;
import com.wsw.fusertaskmanager.service.TesterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 15:29 2020/11/20
 * @Description:
 */
@Service
@Slf4j
public class TesterServiceImpl implements TesterService {
    @Resource
    private TesterMapper testerMapper;

    @RabbitListener(queues = "queueTester")
    @RabbitHandler
    public void receiveMessage(Map messageMap){
        if (MapUtil.isNotEmpty(messageMap)){
            log.info(JSONObject.toJSONString(messageMap));

            Long taskId = MapUtil.getLong(messageMap, "taskId");
            String taskName = MapUtil.getStr(messageMap, "taskName");
            String testerName = MapUtil.getStr(messageMap, "testerName");
            String remark = MapUtil.getStr(messageMap, "remark");
            if (null != taskId && StringUtils.isNotBlank(taskName) && StringUtils.isNotBlank(testerName)){
                insert(taskId, taskName, testerName, remark);
            }
        }
    }

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return testerMapper.insert(taskId, taskName, name, remark);
    }
}
