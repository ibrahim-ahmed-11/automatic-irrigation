package com.misrbanque.task.repositories;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomRefresherRepositoryImpl<T> implements CustomRefresherRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void refresh(T entity) {
        entityManager.refresh(entityManager.merge(entity));
    }

}
