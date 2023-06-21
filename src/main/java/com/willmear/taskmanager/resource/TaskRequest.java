package com.willmear.taskmanager.resource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String title;
    private String assignee;
    private LocalDateTime dueDate;
    private String description;
    private String priority;
    private Integer teamId;


    // id: number;
    // title: string;
    // assignee: string;
    // dueDate: Date;
    // description: string;
    // priority: string; 
    
}

