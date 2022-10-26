package com.misrbanque.task.repositories;

import com.misrbanque.task.entities.PlotEntity;
import com.misrbanque.task.infrastructure.repositories.RefreshingJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlotsRepository extends RefreshingJpaRepository<PlotEntity, Long>, CustomRefresherRepository<PlotEntity> {

}
