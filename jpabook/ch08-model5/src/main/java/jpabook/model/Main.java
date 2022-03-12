package jpabook.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import jpabook.model.entity.Member;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 기능 획득

        CriteriaBuilder cb = em.getCriteriaBuilder();

        try {

            tx.begin(); // 트랜잭션 시작
            // TODO 비즈니스 로직
            String jpql = "select m from Member as m";
            List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
            for (Member member : resultList) {
                System.out.println(member.toString());
            }

            tx.commit();// 트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 트랜잭션 롤백
        } finally {
            em.close(); // 엔티티 매니저 종료
        }

        emf.close(); // 엔티티 매니저 팩토리 종료
    }

}
