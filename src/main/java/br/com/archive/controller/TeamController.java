package br.com.archive.controller;

import br.com.archive.entity.Team;
import br.com.archive.service.TeamService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController()
@RequestMapping("/team")
@Api(value = "Team")
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

    @PostMapping(path = "/addMember/{idPlayer}/{idTeam}")
    public String addMember(@PathVariable("idPlayer") long idPlayer,
                            @PathVariable("idTeam") long idTeam) {
        return this.service.addMember(idPlayer, idTeam);
    }

    @GetMapping()
    public List<Team> get(@RequestParam(required = false) long id) {
        return this.service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long id) {
        this.service.delete(id);
    }
}
