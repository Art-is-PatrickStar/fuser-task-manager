package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.domain.Task;
import com.wsw.fusertaskmanager.mapper.TaskMapper;
import com.wsw.fusertaskmanager.service.MessageService;
import com.wsw.fusertaskmanager.service.RecepienterService;
import com.wsw.fusertaskmanager.service.TaskService;
import com.wsw.fusertaskmanager.service.TesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WangSongWen
 * @Date: Created in 14:28 2020/11/9
 * @Description: task主服务
 *
 * redis缓存:
 * 1.Cacheable: 将查询结果缓存到redis中,(key="#p0")指定传入的第一个参数作为redis的key
 * 2.CachePut: 指定key,将更新的结果同步到redis中
 * 3.CacheEvict: 指定key,删除缓存数据,(allEntries=true)方法调用后将立即清除缓存
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "task")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private RecepienterService recepienterService;
    @Resource
    private TesterService testerService;
    @Resource
    private MessageService messageService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createTask(Task task) {
        int result = 0;
        try {
            // 添加任务
            result = taskMapper.createTask(task);
            //同步调用
            // 调用recepienter服务添加领取人员信息
            //recepienterService.create(task.getTaskId(), task.getTaskName(), task.getRecepientName(), new Date().toString());
            // 调用tester服务添加测试人员信息
            //testerService.create(task.getTaskId(), task.getTaskName(), task.getTesterName(), new Date().toString());
            // RabbitMQ异步调用
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("taskId", task.getTaskId());
            messageMap.put("taskName", task.getTaskName());
            messageMap.put("testerName", task.getTesterName());
            messageMap.put("recepientName", task.getRecepientName());
            messageMap.put("remark", new Date().toString());
            messageService.sendMessage(messageMap);
        } catch (AmqpException e) {
            log.error("消息发送失败: " + e.getMessage());
        }
        log.info("消息发送成功!");

        return result;
    }

    @Override
    @CachePut(key = "#task.taskId")
    public int updateTaskById(Task task) {
        return taskMapper.updateTaskById(task);
    }

    @Override
    @CachePut(key = "#task.taskId")
    public int updateTaskByName(Task task) {
        return taskMapper.updateTaskByName(task);
    }

    @Override
    @CachePut(key = "#p0")
    public int updateTaskStatusByTaskId(Long taskId, char taskStatus) {
        return taskMapper.updateTaskStatusByTaskId(taskId, taskStatus);
    }

    @Override
    @CacheEvict(key = "#p0", allEntries = true)
    public int deleteTaskByTaskId(Long taskId) {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    @Override
    @CacheEvict(key = "#p0", allEntries = true)
    public int deleteTaskByTaskName(String taskName) {
        return taskMapper.deleteTaskByTaskName(taskName);
    }

    @Override
    @Cacheable(key = "#p0")
    public Task selectTaskById(Long taskId) {
        return taskMapper.selectTaskById(taskId);
    }

    @Override
    @Cacheable(key = "#p0")
    public List<Task> selectTaskByName(String taskName) {
        return taskMapper.selectTaskByName(taskName);
    }

    @Override
    @Cacheable(key = "#p0")
    public List<Task> selectTaskByStatus(char taskStatus) {
        return taskMapper.selectTaskByStatus(taskStatus);
    }
}
