package br.com.archive.controller;

import br.com.archive.entity.Player;
import br.com.archive.service.PlayerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/player")
@Api(value = "Player")
public class PlayerController {

    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> get(@RequestParam(required = false) long id) {
        return this.service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Player player) {
        return this.service.create(player);
    }

    @PutMapping("/{id}")
    public String updateAge(@RequestBody Player player,
                            @PathVariable long id) {
        return this.service.updateAge(id, player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long id) {
        this.service.delete(id);
    }

    @PostMapping("/archive")
    @ResponseStatus(HttpStatus.CREATED)
    public String createByArchivo(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return this.service.receivedArchiveToCreate(file);
    }

}
