package com.example.hellofx.product.repository;

import com.example.hellofx.config.EntityManagerProvider;
import com.example.hellofx.product.entity.Menu_Category;
import com.example.hellofx.product.entity.Menu_Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MenuProductRepository {

    EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public int addCategory(Menu_Category menuCategory){ // 카테고리 추가
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select category from Menu_Category where category = :category");
        query.setParameter("category", menuCategory.getCategory());

        List<Menu_Category> existCategory = query.getResultList();

        if (existCategory.isEmpty()){ // 신규추가
            entityManager.persist(menuCategory);
            entityManager.getTransaction().commit();
            return 1;
        }else{
            entityManager.getTransaction().rollback();
            return 0; // 이미 존재함
        }
    }

    public List<Menu_Category> categoryList(){ // 카테고리 목록

        return entityManager.createQuery("select c from Menu_Category c", Menu_Category.class).getResultList();
    }

    public void addProduct(Menu_Product menuProduct){ // 상품등록
        entityManager.getTransaction().begin();
        entityManager.persist(menuProduct);
        entityManager.getTransaction().commit();
    }

    public List<Menu_Product> productList(String category){ // 상품목록

        Query query = entityManager.createQuery("select p from Menu_Product p where category = :category", Menu_Product.class);
        query.setParameter("category", category);

        return query.getResultList();
    }
}
