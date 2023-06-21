package com.willmear.taskmanager;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.willmear.taskmanager.domain.Team;
import com.willmear.taskmanager.domain.User;
import com.willmear.taskmanager.security.auth.AuthenticationService;
import com.willmear.taskmanager.security.auth.RegisterRequest;

@SpringBootApplication
public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner commandLineRunner(AuthenticationService service) {
	// 	return args -> {
	// 		var user = RegisterRequest.builder()
	// 		.firstname("will")
	// 		.lastname("mear")
	// 		.email("w@w.com")
	// 		.password("w")
	// 		.build();
	// 		var user2 = RegisterRequest.builder()
	// 		.firstname("will")
	// 		.lastname("mear")
	// 		.email("ww@ww.com")
	// 		.password("ww")
	// 		.build();
	// 		var user3 = RegisterRequest.builder()
	// 		.firstname("will")
	// 		.lastname("mear")
	// 		.email("www@www.com")
	// 		.password("www")
	// 		.build();
			
			
	// 		System.out.println("User token: " + service.register(user).getAccessToken());
	// 		System.out.println("User token: " + service.register(user2).getAccessToken());
	// 		System.out.println("User token: " + service.register(user3).getAccessToken());

			

	// 	};
	// }

}
