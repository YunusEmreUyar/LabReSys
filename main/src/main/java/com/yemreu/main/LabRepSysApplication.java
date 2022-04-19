package com.yemreu.main;

import com.yemreu.main.model.Report;
import com.yemreu.main.model.Role;
import com.yemreu.main.model.User;
import com.yemreu.main.repository.ReportRepository;
import com.yemreu.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@SpringBootApplication
public class LabRepSysApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	@Autowired
	ReportRepository reportRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LabRepSysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = userRepository.save(new User(
				"Yunus",
				"YunusEmre",
				passwordEncoder.encode("passwd"),
				"Uyar",
				Arrays.asList(
						new Role("ROLE_USER"),
						new Role("ROLE_LABORATORY"),
						new Role("ROLE_ADMIN")
				),
				"12345678910"
		));
		User user2 = userRepository.save(new User(
				"Burak",
				"Burak21",
				passwordEncoder.encode("asd123"),
				"Yıldız",
				Arrays.asList(
						new Role("ROLE_USER")
				),
				"12345673910"
		));
		reportRepository.save(new Report(
				null,
				user2,
				user,
				"Knee pain",
				"Symptoms of knee pain include swelling around the knee, instability when standing, stiffness and popping noises when you move your knee."
		));
		reportRepository.save(new Report(
				null,
				user,
				user,
				"Lorem Ipsum Dolor",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
		));
	}
}
