package com.willmear.taskmanager.resource;

import com.willmear.taskmanager.domain.Team;
import com.willmear.taskmanager.domain.User;
import com.willmear.taskmanager.repository.UserRepository;
import com.willmear.taskmanager.security.config.SecurityUtils;
import com.willmear.taskmanager.service.TeamService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamResource {
    private final TeamService teamService;
    private final UserRepository userRepository;

    @PostMapping("/new-team")
    public ResponseEntity<Team> createTeam(@RequestBody TeamRequest team) throws URISyntaxException {
        User currentUser = userRepository.findByEmail(SecurityUtils.getCurrentUserLogin().map(Object::toString).orElse("")).orElse(null);
        String[] members = team.getMembers();
        List<User> userList = new ArrayList<>();
        userList.add(currentUser);
        for(String member: members) {
            User user = userRepository.findByEmail(member).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            userList.add(user);
        }

        Team newTeam = Team.builder().name(team.getName()).members(userList).admin(currentUser).build();
        System.out.println(newTeam);
        return teamService.createTeam(newTeam);
    }

    @GetMapping()
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/{id}/members")
    public List<User> getTeamMembers(@PathVariable Integer id) {
        return teamService.getTeamMembers(id);
    }

}
