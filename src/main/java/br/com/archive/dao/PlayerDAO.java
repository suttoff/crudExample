package br.com.archive.dao;

import br.com.archive.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PlayerDAO {

    private final MongoOperations mongoOperation;

    public String updateAge(String name, Player player) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        Player playerUpdate = mongoOperation.findOne(query, Player.class);
        if (!Objects.isNull(playerUpdate)) {
            Update update = new Update();
            update.set("age", player.getAge());

            mongoOperation.updateFirst(query, update, Player.class);
            return "Update successfully.";
        }
        return "It was not possible to update, please contact the administration.";
    }
}
