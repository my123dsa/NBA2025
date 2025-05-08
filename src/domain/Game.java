package domain;

import domain.base.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class Game  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5L;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private Integer id;
    private Team team1;
    private Team team2;
    private Integer[] scores;
    private Integer result;

    @Builder
    public Game(Team team1, Team team2, Integer[] scores, Integer result) {
        this.id = idCounter.incrementAndGet();
        this.team1 = team1;
        this.team2 = team2;
        this.scores = scores;
        this.result = result;
    }
}
