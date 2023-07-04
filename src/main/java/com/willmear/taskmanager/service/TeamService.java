package com.willmear.taskmanager.service;

import com.willmear.taskmanager.domain.Team;
import com.willmear.taskmanager.domain.User;
import com.willmear.taskmanager.repository.TeamRepository;
import com.willmear.taskmanager.repository.UserRepository;
import com.willmear.taskmanager.security.config.SecurityUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    // private final UserRepository userRepository;

    public ResponseEntity<Team> createTeam(Team team)  {

        // for (User user : team.getMembers()) {
        //     if (!(userRepository.findByEmail(user.getEmail()).isPresent())) {
        //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        //     }
        // }

        Team result = teamRepository.save(team);
        return ResponseEntity.ok(result);
    }

    public List<Team> getAllTeams() {

        String username2 = SecurityUtils.getCurrentUserLogin().map(Object::toString).orElse("");
        System.out.println(username2);

        return teamRepository.findByUserIsCurrentUser(username2);
    }

    public Team getTeamById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }
}
