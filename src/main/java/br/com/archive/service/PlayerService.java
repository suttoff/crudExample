package br.com.archive.service;

import br.com.archive.dao.PlayerDAO;
import br.com.archive.entity.Player;
import br.com.archive.repository.PlayerRepository;
import com.univocity.parsers.fixed.FixedWidthParser;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    @Getter
    @Setter
    private Integer length = 0;

    private static final String TEXT_PLAIN = "text/plain";

    private final FixedWidthParser fixedWidthParser;
    private final PlayerRepository repository;
    private final PlayerDAO playerDAO;


    public List<Player> get(long id) {
        if (Objects.isNull(id)) {
            return this.repository.findAll();
        } else {
            List<Player> players = new ArrayList<>();
            Optional<Player> player = this.repository.findById(id);

            player.ifPresent(players::add);
            return players;
        }
    }

    public String create(Player player) {
        this.repository.save(player);
        return "Player successfully included.";
    }

    public String updateAge(long id, Player player) {
        return this.playerDAO.updateAge(id, player);
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }

    public String receivedArchiveToCreate(MultipartFile file) throws IOException {
        InputStream inputStream;
        List<String[]> allRows = null;
        List<List<String[]>> list = new ArrayList<>();

        if (file.getContentType().equals(TEXT_PLAIN)) {
            inputStream = file.getInputStream();
            String readLine = getbufferedReaderString(inputStream);
            setLength(readLine.length());

            allRows = Try.of(() -> this.fixedWidthParser.parseAll(file.getInputStream()))
                    .onFailure(Exception::new)
                    .get();
            list.add(allRows);

        }

        if (allRows != null) {
            allRows.stream().map(line -> this.repository.save(buildPlayer(line))).collect(Collectors.toList());
        }
        return "Player successfully included by archive.";
    }
    private Player buildPlayer(String[] line) {
        Player player = new Player();
        player.setName(line[0]);
        player.setAge(Integer.parseInt(line[1]));
        return player;
    }

    private String getbufferedReaderString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();
    }

}
