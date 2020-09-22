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
    private final SequenceGeneratorService serviceId;

    public String create(Team team) {
        team.setId(serviceId.generateSequence(Team.SEQUENCE_NAME));
        this.repository.save(team);
        return "Team successfully included.";
    }

    public List<Team> get() {
        return this.repository.findAll();
    }

}
