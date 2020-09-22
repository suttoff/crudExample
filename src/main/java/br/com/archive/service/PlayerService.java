package br.com.archive.service;

import br.com.archive.dao.PlayerDAO;
import br.com.archive.entity.Player;
import br.com.archive.repository.PlayerRepository;
import com.univocity.parsers.fixed.FixedWidthParser;
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
    private final SequenceGeneratorService serviceId;


    public List<Player> get() {
        return this.repository.findAll();
    }

    public String create(Player player) {
        player.setId(serviceId.generateSequence(Player.SEQUENCE_NAME));
        this.repository.save(player);
        return "Player successfully included.";
    }

    public String updateAge(String name, Player player) {
        return this.playerDAO.updateAge(name, player);
    }

    public void delete(String name) {
        this.repository.deleteByName(name);
    }

    public String receivedArchiveToCreate(MultipartFile file) throws IOException {

        InputStream inputStream;
        List<String[]> allRows = null;
        List<List<String[]>> list = new ArrayList<>();

        if (file.getContentType().equals(TEXT_PLAIN)) {
            inputStream = file.getInputStream();
            String readLine = getbufferedReaderString(inputStream);
            setLength(readLine.length());

            allRows = Try.of(() -> fixedWidthParser.parseAll(file.getInputStream()))
                    .onFailure(Exception::new)
                    .get();
            list.add(allRows);
        }

        if (allRows != null) {
            allRows.stream().map(line -> repository.save(buildPlayer(line))).collect(Collectors.toList());
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
