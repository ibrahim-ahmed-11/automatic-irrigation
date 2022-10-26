package com.misrbanque.task.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomRefresherRepository<T> {

    void refresh(T fawryPaymentEntity);

}
