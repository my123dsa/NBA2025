package domain;

import lombok.*;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HeadCoach extends Person implements Serializable {
    private static final long serialVersionUID = 4L;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private Integer id;
    private Integer teamBonus;

    @Builder
    public HeadCoach(String name, Integer age, Long salary, Integer id, Integer teamBonus) {
        super(name, age, salary);
        this.id = idCounter.incrementAndGet();
        this.teamBonus = teamBonus;
    }
}
