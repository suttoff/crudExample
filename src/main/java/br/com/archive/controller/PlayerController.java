package br.com.archive.controller;

import br.com.archive.entity.Player;
import br.com.archive.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> get() {
        return this.service.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Player player) {
        return this.service.create(player);
    }

    @PutMapping("/{name}")
    public String updateAge(@RequestBody Player player,
                            @PathVariable String name) {
        return this.service.updateAge(name, player);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String name) {
        this.service.delete(name);
    }

    @PostMapping("/archive")
    @ResponseStatus(HttpStatus.CREATED)
    public String createByArchivo(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        return this.service.receivedArchiveToCreate(file);
    }

}
