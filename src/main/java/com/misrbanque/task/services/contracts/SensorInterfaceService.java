package com.misrbanque.task.services.contracts;

import com.misrbanque.task.models.integration.SensorRequest;

public interface SensorInterfaceService {

    boolean sendSensorReading(SensorRequest request);

}
