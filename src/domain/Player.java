package domain;


import lombok.*;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Player extends Person implements Serializable {
    private static final long serialVersionUID = 2L;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private Integer id;
    private Integer height;
    private Integer wingSpan;
    private String position;
    private Stats stats;

    private Boolean isFA;

//    private transient Team team;

    @Builder
    public Player(String name, Integer age, Long salary, Integer id, Integer height, Integer wingSpan, String position, Stats stats,Boolean isFA) {
        super(name, age, salary);
        this.id = idCounter.incrementAndGet();
        this.height = height;
        this.wingSpan = wingSpan;
        this.position = position;
        this.stats = stats;
        this.isFA = isFA != null ? isFA : false;
    }
}
