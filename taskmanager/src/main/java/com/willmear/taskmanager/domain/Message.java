package com.willmear.taskmanager.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Message {
    @GeneratedValue
    @Id
    private Integer id;
    private String message;
    private LocalDateTime timestamp;
    @ManyToOne
    private User author;
    @ManyToOne
    private Team team;
}
