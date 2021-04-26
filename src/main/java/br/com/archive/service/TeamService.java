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
import java.util.Optional;

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

    public String addMember(long idPlayer, long idTeam) {
        Optional<Player> player = this.playerRepository.findById(idPlayer);
        if (!player.isPresent()) {
            return "The informed player does not exist";
        }
        return this.teamDAO.addMember(idTeam, player.get());
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }

    public List<Team> get(Long id) {
        if (Objects.isNull(id)) {
            return this.repository.findAll();
        } else {
            List<Team> teams = new ArrayList<>();
            Optional<Team> team = this.repository.findById(id);

            team.ifPresent(teams::add);
            return teams;
        }
    }
}
