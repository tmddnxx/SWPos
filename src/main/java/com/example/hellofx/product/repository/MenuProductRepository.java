package com.example.hellofx.product.repository;

import com.example.hellofx.config.EntityManagerProvider;
import com.example.hellofx.product.entity.Menu_Category;
import com.example.hellofx.product.entity.Menu_Product;

import javax.persistence.EntityManager;
import java.util.List;

public class MenuProductRepository {

    EntityManager entityManager = EntityManagerProvider.getEntityManager();

    public void addCategory(Menu_Category menuCategory){ // 카테고리 추가
        entityManager.getTransaction().begin();
        entityManager.persist(menuCategory);
        entityManager.getTransaction().commit();
    }

    public List<Menu_Category> categoryList(){ // 카테고리 목록

        return entityManager.createQuery("select c from Menu_Category c", Menu_Category.class).getResultList();
    }

    public void addProduct(Menu_Product menuProduct){
        entityManager.getTransaction().begin();
        entityManager.persist(menuProduct);
        entityManager.getTransaction().commit();
    }
}
