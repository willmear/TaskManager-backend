package com.willmear.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import com.willmear.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.willmear.taskmanager.domain.CalendarEvent;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {

    List<CalendarEvent> findByTeamId(Integer id);

    Optional<CalendarEvent> findByTaskId(Integer id);
}
