package com.willmear.taskmanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.willmear.taskmanager.domain.CalendarEvent;
import com.willmear.taskmanager.repository.CalendarEventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    public List<CalendarEvent> getAllCalendarEvents(Integer id) {
        return calendarEventRepository.findByTeamId(id);
    }

    public ResponseEntity<CalendarEvent> createCalendarEvent(CalendarEvent calendarEvent) {

        CalendarEvent result = CalendarEvent.builder()
                .title(calendarEvent.getTitle())
                .description(calendarEvent.getDescription())
                .startDate(calendarEvent.getStartDate())
                .endDate(calendarEvent.getEndDate())
                .team(calendarEvent.getTeam())
                .assignee(calendarEvent.getAssignee())
                .build();

        return ResponseEntity.ok(calendarEventRepository.save(result));
    }

    public ResponseEntity<Void> deleteCalendarEvent(Integer id) {
        calendarEventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
