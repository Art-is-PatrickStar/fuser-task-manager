package com.wsw.fusertaskmanager.service.impl;

import com.wsw.fusertaskmanager.domain.Task;
import com.wsw.fusertaskmanager.mapper.TaskMapper;
import com.wsw.fusertaskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 14:28 2020/11/9
 * @Description:
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    @Override
    public int createTask(Task task) {
        return taskMapper.createTask(task);
    }

    @Override
    public int updateTaskById(Task task) {
        return taskMapper.updateTaskById(task);
    }

    @Override
    public int updateTaskByName(Task task) {
        return taskMapper.updateTaskByName(task);
    }

    @Override
    public int updateTaskStatusByTaskId(Long taskId, char taskStatus) {
        return taskMapper.updateTaskStatusByTaskId(taskId, taskStatus);
    }

    @Override
    public int deleteTaskByTaskId(Long taskId) {
        return taskMapper.deleteTaskByTaskId(taskId);
    }

    @Override
    public int deleteTaskByTaskName(String taskName) {
        return taskMapper.deleteTaskByTaskName(taskName);
    }

    @Override
    public Task selectTaskById(Long taskId) {
        return taskMapper.selectTaskById(taskId);
    }

    @Override
    public List<Task> selectTaskByName(String taskName) {
        return taskMapper.selectTaskByName(taskName);
    }

    @Override
    public List<Task> selectTaskByStatus(char taskStatus) {
        return taskMapper.selectTaskByStatus(taskStatus);
    }
}
