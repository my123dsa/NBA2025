package domain;

import domain.base.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Team extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private Integer id;
    private String name;
    private Long money;
    private Integer wins;
    private Integer totalGames;
    private HeadCoach headCoach;
    private List<Player> players;

    @Builder
    public Team(String name, Long money, Integer wins, Integer totalGames, HeadCoach headCoach, List<Player> players) {
        this.id = idCounter.incrementAndGet();
        this.name = name;
        this.money = money;
        this.wins = wins;
        this.totalGames = totalGames;
        this.headCoach = headCoach;
        this.players = players;
    }
}
