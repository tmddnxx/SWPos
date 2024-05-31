package com.example.hellofx.login.repository;


import com.example.hellofx.config.EntityManagerProvider;
import com.example.hellofx.login.entity.Member;
import org.hibernate.QueryException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


public class MemberRepository {

    EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public List<Member> findAll() { // 전체찾기
        return entityManager.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id) { // id찾기
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    public void save(Member member) { // 저장 & 수정
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(member); // 회원 정보를 저장
            transaction.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // 롤백
            }
            e.printStackTrace();
        }
    }

    public void delete(Member member) { // 삭제
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Long login(String id, String pw){
        try{
            Query query = entityManager.createQuery("select count(mno) from Member where id = :id and pw = :pw");
            query.setParameter("id", id);
            query.setParameter("pw", pw);

            return (Long) query.getSingleResult(); // 로그인 성공 시 1 , 실패 시 0

        } catch (QueryException e){
            e.printStackTrace();
            return null;
        }
    }

}
