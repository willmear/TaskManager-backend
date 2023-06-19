package com.willmear.taskmanager.resource;

import com.willmear.taskmanager.domain.Task;
import com.willmear.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/task")
@RestController
@RequiredArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public List<Task> getAllTasks(@PathVariable Integer id) {
        return taskService.getAllTasks(id);
    }

    @GetMapping("/{id}/open")
    public List<Task> getAllOpenTasks(@PathVariable Integer id) {
        return taskService.getAllOpenTasks(id);
    }

    @GetMapping("/{id}/closed")
    public List<Task> getAllClosedTasks(@PathVariable Integer id) {
        return taskService.getAllClosedTasks(id);
    }

    @GetMapping("/{teamId}/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Integer teamId, @PathVariable Integer taskId) {
        return taskService.getTask(teamId, taskId);
    }

    @PutMapping("/update/{teamId}/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer teamId, @PathVariable Integer taskId, @RequestBody TaskPostRequest task) {
        return taskService.updateTask(task, teamId, taskId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }

}
