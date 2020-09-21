package br.com.archive.entity;

import br.com.archive.domain.StatusType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "team")
public class Team {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "tag is required")
    @Size(min=3 ,max=3, message="tag must be the size {max}")
    private String tag;

    //@NotNull(message = "statusType is required")
    private StatusType statusType;

    List<Player> players;

}
