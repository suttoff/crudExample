package br.com.archive.repository;

import br.com.archive.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, Long> {

    List<Team> findAll();
}
