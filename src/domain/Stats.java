package domain;

import domain.base.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Stats  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3L;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private Integer id;
    private Integer shoot;
    private Integer pass;
    private Integer dribble;
    private Integer rebound;
    private Integer block;
    private Integer steal;
    private Integer point3;


    @Builder
    public Stats( Integer shoot, Integer pass, Integer dribble, Integer rebound, Integer block, Integer steal, Integer point3) {
        this.id = idCounter.incrementAndGet();
        this.shoot = shoot;
        this.pass = pass;
        this.dribble = dribble;
        this.rebound = rebound;
        this.block = block;
        this.steal = steal;
        this.point3 = point3;
    }
}
