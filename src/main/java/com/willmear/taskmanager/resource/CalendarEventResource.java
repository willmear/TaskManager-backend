package com.willmear.taskmanager.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.willmear.taskmanager.domain.CalendarEvent;
import com.willmear.taskmanager.service.CalendarEventService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/calendar-event")
@RestController
@RequiredArgsConstructor
public class CalendarEventResource {

    private final CalendarEventService calendarEventService;

    @GetMapping("/{id}")
    public List<CalendarEvent> getAllCalendarEvents(@PathVariable Integer id) {
        return calendarEventService.getAllCalendarEvents(id);
    }

    @PostMapping
    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        return calendarEventService.createCalendarEvent(calendarEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendarEvent(@PathVariable Integer id) {
        return calendarEventService.deleteCalendarEvent(id);
    }

    
}
