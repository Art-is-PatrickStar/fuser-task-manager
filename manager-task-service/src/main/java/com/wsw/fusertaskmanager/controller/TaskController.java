package com.wsw.fusertaskmanager.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.wsw.fusertaskmanager.domain.CommonResult;
import com.wsw.fusertaskmanager.domain.Task;
import com.wsw.fusertaskmanager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 15:03 2020/11/9
 * @Description: 前后端交互接口
 *
 * 1. Get请求
 *    1.1 xxx/task/select/{taskId}形式 -> @PathVariable("taskId")
 *    1.2 xxx/task/select?taskId=1形式 -> @RequestParam("taskId")
 * 2. Post请求
 *    前台请求参数设置 -> contentType: "application/json" -> xxx/task/create形式 -> @RequestBody
 */
@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public CommonResult createTask(@RequestBody Task task) {
        int result = taskService.createTask(task);
        if (result > 0){
            return new CommonResult(200, "任务创建成功!", result);
        }else {
            return new CommonResult(500, "任务创建失败!", null);
        }
    }

    @PutMapping("/update/byid")
    public CommonResult updateTaskById(@RequestBody Task task){
        Long taskId = task.getTaskId();
        int result = taskService.updateTaskById(task, taskId);
        if (result > 0){
            return new CommonResult(200, "任务更新成功!", result);
        }else {
            return new CommonResult(500, "任务更新失败!", null);
        }
    }

    @PutMapping("/update/byname")
    public CommonResult updateTaskByName(@RequestBody Task task){
        String taskName = task.getTaskName();
        int result = taskService.updateTaskByName(task, taskName);
        if (result > 0){
            return new CommonResult(200, "任务更新成功!", result);
        }else {
            return new CommonResult(500, "任务更新失败!", null);
        }
    }

    @GetMapping("/updatestatus/byid")
    public CommonResult updateTaskStatusByTaskId(@RequestParam("taskId") Long taskId, @RequestParam("taskStatus") char taskStatus){
        int result = taskService.updateTaskStatusByTaskId(taskId, taskStatus);
        if (result > 0){
            return new CommonResult(200, "任务状态更新成功!", result);
        }else {
            return new CommonResult(500, "任务状态更新失败!", null);
        }
    }

    @DeleteMapping("/delete/byid")
    public CommonResult deleteTaskByTaskId(@RequestParam("taskId") Long taskId){
        int result = taskService.deleteTaskByTaskId(taskId);
        if (result > 0){
            return new CommonResult(200, "任务删除成功!", result);
        }else {
            return new CommonResult(500, "任务删除失败!", null);
        }
    }

    @DeleteMapping("/delete/byname")
    public CommonResult deleteTaskByTaskName(@RequestParam("taskName") String taskName){
        int result = taskService.deleteTaskByTaskName(taskName);
        if (result > 0){
            return new CommonResult(200, "任务删除成功!", result);
        }else {
            return new CommonResult(500, "任务删除失败!", null);
        }
    }

    @GetMapping("/task/select/{taskId}")
    public CommonResult selectTaskById(@PathVariable("taskId") Long taskId){
        Task task = taskService.selectTaskById(taskId);
        if (null != task){
            return new CommonResult(200, "获取任务成功!", task);
        }else {
            return new CommonResult(500, "获取任务失败!", null);
        }
    }

    @GetMapping("/task/select/{taskName}")
    public CommonResult selectTaskByName(@PathVariable("taskName") String taskName){
        Task task = taskService.selectTaskByName(taskName);
        if (null != task){
            return new CommonResult(200, "获取任务成功!", task);
        }else {
            return new CommonResult(500, "获取任务失败!", null);
        }
    }

    @GetMapping("/task/select/{taskStatus}")
    public CommonResult selectTaskByStatus(@PathVariable("taskStatus") char taskStatus){
        List<Task> tasks = taskService.selectTaskByStatus(taskStatus);
        if (CollectionUtil.isNotEmpty(tasks)){
            return new CommonResult(200, "获取任务成功!", tasks);
        }else {
            return new CommonResult(500, "获取任务失败!", null);
        }
    }
}
