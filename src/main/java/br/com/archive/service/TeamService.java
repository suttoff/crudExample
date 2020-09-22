package br.com.archive.service;

import br.com.archive.dao.TeamDAO;
import br.com.archive.entity.Player;
import br.com.archive.entity.Team;
import br.com.archive.repository.PlayerRepository;
import br.com.archive.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository repository;
    private final PlayerRepository playerRepository;
    private final TeamDAO teamDAO;
    private final SequenceGeneratorService serviceId;

    public String create(Team team) {
        team.setId(serviceId.generateSequence(Team.SEQUENCE_NAME));
        this.repository.save(team);
        return "Team successfully included.";
    }

    public String addMember(Long idPlayer, Long idTeam) {
        Player player = this.playerRepository.findById(idPlayer).get();
        return this.teamDAO.addMember(idTeam, player);
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
