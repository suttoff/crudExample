package br.com.archive.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Transient
    public static final String SEQUENCE_NAME = "player_sequence";

    @Id
    private long id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "name is required")
    private int age;
}
