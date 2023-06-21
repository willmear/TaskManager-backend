package com.willmear.taskmanager.service;

import com.willmear.taskmanager.domain.CalendarEvent;
import com.willmear.taskmanager.domain.Task;
import com.willmear.taskmanager.enums.Priority;
import com.willmear.taskmanager.enums.Status;
import com.willmear.taskmanager.repository.CalendarEventRepository;
import com.willmear.taskmanager.repository.TaskRepository;
import com.willmear.taskmanager.repository.TeamRepository;
import com.willmear.taskmanager.repository.UserRepository;
import com.willmear.taskmanager.resource.TaskPostRequest;
import com.willmear.taskmanager.resource.TaskRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final CalendarEventRepository calendarRepository;

    public ResponseEntity<Task> createTask(TaskRequest task) {

        Task newTask = Task.builder()
                .title(task.getTitle())
                .status(Status.OPEN)
                .startDate(LocalDateTime.now())
                .team(teamRepository.findById(task.getTeamId()).orElse(null))
                .assignee(userRepository.findByEmail(task.getAssignee()).orElse(null))
                .dueDate(task.getDueDate())
                .description(task.getDescription())
                .priority(Priority.valueOf(task.getPriority().toUpperCase()))
                .build();

        Task result = taskRepository.save(newTask);

        CalendarEvent newCalendarEvent = CalendarEvent.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .startDate(LocalDateTime.now())
                .endDate(task.getDueDate())
                .assignee(userRepository.findByEmail(task.getAssignee()).orElse(null))
                .team(teamRepository.findById(task.getTeamId()).orElse(null))
                .taskId(result.getId())
                .build();
        calendarRepository.save(newCalendarEvent);

        return ResponseEntity.ok(result);
    }

    public List<Task> getAllTasks(Integer id) {
        return taskRepository.findByTeamId(id);
    }

    public List<Task> getAllOpenTasks(Integer id) {

        return taskRepository.findByTeamIdAndStatus(id, Status.OPEN);

    }

    public List<Task> getAllClosedTasks(Integer id) {

        return taskRepository.findByTeamIdAndStatus(id, Status.CLOSED);

    }

    public ResponseEntity<Task> getTask(Integer teamId, Integer taskId) {
        return ResponseEntity.ok(taskRepository.findByTeamIdAndId(teamId, taskId));
    }

    public ResponseEntity<Task> updateTask(TaskPostRequest newTask, Integer teamId, Integer taskId) {

        //TODO: Update calendar event when task is updated

        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setStatus(newTask.getStatus());
                    task.setStartDate(newTask.getStartDate());
                    task.setTeam(teamRepository.findById(teamId).orElse(null));
                    task.setAssignee(userRepository.findById(newTask.getAssignee()).orElse(null));
                    task.setDueDate(newTask.getDueDate());
                    task.setDescription(newTask.getDescription());
                    task.setPriority(newTask.getPriority());
                    Task updatedTask = taskRepository.save(task);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElse(ResponseEntity.notFound().build());
      
    }

    public ResponseEntity<Void> deleteTask(Integer id) {

        CalendarEvent calendarEvent = calendarRepository.findByTaskId(id).orElse(null);
        if (calendarEvent != null) {
            calendarRepository.deleteById(calendarEvent.getId());
        }

        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
