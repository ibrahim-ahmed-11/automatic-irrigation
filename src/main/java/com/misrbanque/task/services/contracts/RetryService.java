package com.misrbanque.task.services.contracts;

import com.misrbanque.task.models.integration.SensorRequest;

public interface RetryService {

    void retrySend(SensorRequest request, int maxNumberOfRetries) throws Exception;

}
