package com.misrbanque.task.repositories;

import com.misrbanque.task.entities.TimeSlotEntity;
import com.misrbanque.task.infrastructure.repositories.RefreshingJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotsRepository extends RefreshingJpaRepository<TimeSlotEntity, Long>, CustomRefresherRepository<TimeSlotEntity> {


}
