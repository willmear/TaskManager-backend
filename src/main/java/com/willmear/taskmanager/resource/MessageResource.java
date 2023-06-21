package com.willmear.taskmanager.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willmear.taskmanager.domain.Message;
import com.willmear.taskmanager.service.MessageService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/discussion")
@RestController
@RequiredArgsConstructor
public class MessageResource {

    private final MessageService messageService;
    
    @PostMapping("/{id}")
    public ResponseEntity<Message> createMessage(@RequestBody Message message, @PathVariable Integer id) {
        return messageService.createMessage(message, id);
    }

    @GetMapping("/{id}")
    public List<Message> getAllMessage(@PathVariable Integer id) {
        return messageService.getAllMessage(id);
    }
}
