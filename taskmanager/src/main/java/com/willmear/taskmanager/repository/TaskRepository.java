package com.willmear.taskmanager.repository;

import com.willmear.taskmanager.domain.Task;
import com.willmear.taskmanager.enums.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTeamId(Integer id);

    List<Task> findByTeamIdAndStatus(Integer id, Status open);

    Task findByTeamIdAndId(Integer teamId, Integer taskId);
}
