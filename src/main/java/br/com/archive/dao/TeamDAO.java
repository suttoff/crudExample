package br.com.archive.dao;

import br.com.archive.entity.Player;
import br.com.archive.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamDAO {

    private final MongoOperations mongoOperation;

    public String addMember(Long id, Player player) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(id));
        Team team = this.mongoOperation.findOne(query, Team.class);

        if (!Objects.isNull(team)) {
            if (Objects.isNull(team.getPlayers())) {
                team.setPlayers(new ArrayList<>());
            }

            boolean haveDuplicate = team.getPlayers().contains(player);
            if (haveDuplicate) {
                return "Unable to add, player already belongs to the team";
            }

            team.getPlayers().add(player);
            update.set("players", team.getPlayers());
            this.mongoOperation.updateFirst(query, update, Team.class);
            return "Member added successfully.";
        }
        return "Unable to add, please contact the administration.";
    }
}
