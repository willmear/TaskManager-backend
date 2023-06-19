package com.willmear.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willmear.taskmanager.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByTeamId(Integer teamId);
    
}
