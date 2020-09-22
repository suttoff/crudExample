package br.com.archive.service;

import br.com.archive.entity.Team;
import br.com.archive.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public String addMember(Long idPlayer, Long idTeam) {
        return "Player successfully included.";
    }

    public List<Team> get(Long id) {
        if (Objects.isNull(id)) {
            return this.repository.findAll();
        } else {
            List<Team> Teams = new ArrayList<>();
            Teams.add(this.repository.findById(id).orElse(new Team()));

            if (Teams.get(0).getId() == 0) {
                Teams.clear();
            }
            return Teams;
        }
    }

}
