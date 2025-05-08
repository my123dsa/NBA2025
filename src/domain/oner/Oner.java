package domain.oner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Oner {
    private Integer teamId;
    private String teamName;

    @Builder
    public Oner(Integer teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
