package com.misrbanque.task.repositories;

import com.misrbanque.task.configuration.repositories.RefreshingJpaRepository;
import com.misrbanque.task.entities.TimeSlotEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotsRepository extends RefreshingJpaRepository<TimeSlotEntity, Long>, CustomRefresherRepository<TimeSlotEntity> {


}
