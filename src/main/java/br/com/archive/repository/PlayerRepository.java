package br.com.archive.repository;

import br.com.archive.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, Long> {

    List<Player> findAll();
}
