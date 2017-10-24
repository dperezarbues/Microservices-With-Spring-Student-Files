package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Player;
import com.example.demo.domain.Team;
import com.example.demo.repository.TeamRepository;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private TeamRepository teamRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		List<Team> teams = new ArrayList<>();

		Set<Player> players = new HashSet<>();
		
		players.add(new Player("Pepe","Escolta"));
		players.add(new Player("Luis","Pivot"));
		
		
		teams.add(new Team("CAI","Zaragoza","Leon",players));
		
		players = new HashSet<>();
		
		players.add(new Player("Antonio","Escolta"));
		players.add(new Player("Carlos","Pivot"));
		players.add(new Player("Mario","Base"));
		
		teams.add(new Team("CD Huesca","Huesca","Elefante",players));

		teamRepository.save(teams);
	}
}
