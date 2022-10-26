package com.misrbanque.task.services.integration;

import com.misrbanque.task.models.integration.SensorRequest;
import com.misrbanque.task.services.contracts.SensorIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorIntegrationServiceImpl implements SensorIntegrationService {

    @Override
    public void sendSensorReading(SensorRequest request) {
        //DO BUSINESS SENSOR HERE
    }
}
