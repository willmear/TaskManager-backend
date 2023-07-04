package com.willmear.taskmanager.repository;

import com.willmear.taskmanager.domain.Team;
import com.willmear.taskmanager.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Query("select team from Team team join team.members members where members.email =:username")
    List<Team> findByUserIsCurrentUser(@Param("username") String username);

    @Query("select members from Team team join team.members members where team.id =:id")
    List<User> findMembersById(Integer id);

}
