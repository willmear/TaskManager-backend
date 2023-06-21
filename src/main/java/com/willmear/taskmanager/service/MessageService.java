package com.willmear.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.willmear.taskmanager.domain.Message;
import com.willmear.taskmanager.domain.User;
import com.willmear.taskmanager.repository.MessageRepository;
import com.willmear.taskmanager.repository.TeamRepository;
import com.willmear.taskmanager.repository.UserRepository;
import com.willmear.taskmanager.security.config.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public ResponseEntity<Message> createMessage(Message message, Integer teamId) {

        User currentUser = userRepository.findByEmail(SecurityUtils.getCurrentUserLogin().map(Object::toString).orElse("")).orElse(null);

        Message result = Message.builder()
        .message(message.getMessage())
        .timestamp(LocalDateTime.now())
        .author(currentUser)
        .team(teamRepository.findById(teamId).orElse(null))
        .build();

        return ResponseEntity.ok(messageRepository.save(result));
    }

    public List<Message> getAllMessage(Integer teamId) {

        return messageRepository.findAllByTeamId(teamId);
    }
    
}
