package br.com.archive.controller;

import br.com.archive.entity.Team;
import br.com.archive.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController()
@RequestMapping("/team")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody Team team) {
       return this.service.create(team);
    }

    @GetMapping()
    public List<Team> get(@RequestParam(required = false) String id) {
        return this.service.get(id);
    }
}
