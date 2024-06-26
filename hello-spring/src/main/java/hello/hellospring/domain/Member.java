package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    // DB가 알아서 생성해주는 것은 IDENTITY라고 한다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
