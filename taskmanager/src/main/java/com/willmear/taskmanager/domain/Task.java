package com.willmear.taskmanager.domain;

import com.willmear.taskmanager.enums.Priority;
import com.willmear.taskmanager.enums.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Task {
    @GeneratedValue
    @Id
    private Integer id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User assignee;
    @ManyToOne
    private Team team;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;


}
