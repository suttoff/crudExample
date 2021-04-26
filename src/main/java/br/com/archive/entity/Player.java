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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    private long id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "name is required")
    private int age;
}
