package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
// 상속관계 매핑
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// JOINED : 정교화된 스타일
// SINGLE_TABLE : 한 테이블에 다 때려박음
// TABLE_PER_CLASS : 북, 무비, 앨범 하나씩 테이블이 생성됨
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {
    private String author;
    private String isbn;
}
