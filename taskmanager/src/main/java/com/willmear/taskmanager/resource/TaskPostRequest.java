package com.willmear.taskmanager.resource;

import java.time.LocalDateTime;

import com.willmear.taskmanager.enums.Priority;
import com.willmear.taskmanager.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskPostRequest {
    
    private Integer id;
    private String title;
    private Integer assignee;
    private LocalDateTime dueDate;
    private LocalDateTime startDate;
    private String description;
    private Priority priority;
    private Status status;
    
}
