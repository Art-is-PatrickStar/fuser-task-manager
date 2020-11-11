package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.domain.Task;
import com.wsw.fusertaskmanager.mapper.TaskMapper;
import com.wsw.fusertaskmanager.service.TaskService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 14:28 2020/11/9
 * @Description:
 */
@Service
@CacheConfig(cacheNames = "task")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    @Override
    @CachePut(key = "task.taskId")
    public int createTask(Task task) {
        return taskMapper.createTask(task);
    }

    @Override
    @Cacheable(key = "#task.taskId")
    public int updateTaskById(Task task) {
        return taskMapper.updateTaskById(task);
    }

    @Override
    @Cacheable(key = "#task.taskId")
    public int updateTaskByName(Task task) {
        return taskMapper.updateTaskByName(task);
    }

    @Override
    @Cacheable(key = "#taskId")
    public int updateTaskStatusByTaskId(Long taskId, char taskStatus) {
        return taskMapper.updateTaskStatusByTaskId(taskId, taskStatus);
    }

    @Override
    @CacheEvict(key = "#taskId")
    public int deleteTaskByTaskId(Long taskId) {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    @Override
    @CacheEvict(key = "#taskName")
    public int deleteTaskByTaskName(String taskName) {
        return taskMapper.deleteTaskByTaskName(taskName);
    }

    @Override
    @Cacheable(key = "#taskId")
    public Task selectTaskById(Long taskId) {
        return taskMapper.selectTaskById(taskId);
    }

    @Override
    @Cacheable(key = "#taskName")
    public List<Task> selectTaskByName(String taskName) {
        return taskMapper.selectTaskByName(taskName);
    }

    @Override
    @Cacheable(key = "#taskStatus")
    public List<Task> selectTaskByStatus(char taskStatus) {
        return taskMapper.selectTaskByStatus(taskStatus);
    }
}
