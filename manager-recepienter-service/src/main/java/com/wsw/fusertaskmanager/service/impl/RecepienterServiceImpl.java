package com.wsw.fusertaskmanager.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.wsw.fusertaskmanager.mapper.RecepienterMapper;
import com.wsw.fusertaskmanager.service.RecepienterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 14:21 2020/11/20
 * @Description:
 */
@Service
@Slf4j
public class RecepienterServiceImpl implements RecepienterService {
    @Resource
    private RecepienterMapper recepienterMapper;

    @RabbitListener(queues = "queueRecepienter")
    @RabbitHandler
    public void receiveMessage(Map messageMap){
        if (MapUtil.isNotEmpty(messageMap)){
            log.info(JSONObject.toJSONString(messageMap));

            Long taskId = MapUtil.getLong(messageMap, "taskId");
            String taskName = MapUtil.getStr(messageMap, "taskName");
            String recepienterName = MapUtil.getStr(messageMap, "recepienterName");
            String remark = MapUtil.getStr(messageMap, "remark");
            if (null != taskId && StringUtils.isNotBlank(taskName) && StringUtils.isNotBlank(recepienterName)){
                insert(taskId, taskName, recepienterName, remark);
            }
        }
    }

    @Override
    public int insert(Long taskId, String taskName, String name, String remark) {
        return recepienterMapper.insert(taskId, taskName, name, remark);
    }
}
