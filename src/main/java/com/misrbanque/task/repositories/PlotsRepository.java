package com.misrbanque.task.repositories;

import com.misrbanque.task.configuration.repositories.RefreshingJpaRepository;
import com.misrbanque.task.entities.PlotEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotsRepository extends RefreshingJpaRepository<PlotEntity, Long>, CustomRefresherRepository<PlotEntity> {

}
