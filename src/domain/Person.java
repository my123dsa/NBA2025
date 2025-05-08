package domain;

import domain.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
abstract class Person  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 66L;
    private String name;
    private Integer age;
    private Long salary;

    public Person(String name, Integer age, Long salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
