package br.com.archive.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@Validated
@NotNull(message = "player is required")
@Document(collection = "player")
public class Player {

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "name is required")
    private int age;

    public Player() {

    }
}
