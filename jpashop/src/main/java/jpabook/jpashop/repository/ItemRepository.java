package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Item item) {
        // 새로 생성하는 객체
        if (item.getId() == null) {
            em.persist(item);
        }
        // 원래 있는것(jpa가 db에서 가져옴)
        else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
