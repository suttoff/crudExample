package br.com.archive.service;

import br.com.archive.entity.Team;
import br.com.archive.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository repository;

    public String create(Team team) {
        this.repository.save(team);
        return "Team successfully included.";
    }

    public List<Team> get() {
        return this.repository.findAll();
    }

}
