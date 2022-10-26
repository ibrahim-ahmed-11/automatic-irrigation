package com.misrbanque.task.services.contracts;

import com.misrbanque.task.models.integration.SensorRequest;

public interface SensorIntegrationService {

    void sendSensorReading(SensorRequest request);

}
