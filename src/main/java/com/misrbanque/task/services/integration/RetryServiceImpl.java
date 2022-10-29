package com.misrbanque.task.services.integration;

import com.misrbanque.task.enums.PlotStatusEnum;
import com.misrbanque.task.models.integration.SensorRequest;
import com.misrbanque.task.services.contracts.AlertService;
import com.misrbanque.task.services.contracts.PlotUpdateService;
import com.misrbanque.task.services.contracts.RetryService;
import com.misrbanque.task.services.contracts.SensorInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class RetryServiceImpl implements RetryService {

    private final SensorInterfaceService interfaceService;
    private final AlertService alertService;
    private final PlotUpdateService plotUpdateService;

    @Retryable(value = Exception.class, maxAttemptsExpression = "${server.integration.max-retries-count}", backoff = @Backoff(delay = 1000))
    @Override
    public void retrySend(SensorRequest request, int maxNumberOfRetries) throws Exception {

        boolean isDataSent = interfaceService.sendSensorReading(request);

        if (!isDataSent)
            throw new Exception("Failed to send data again!");
        else
            plotUpdateService.updatePlotStatus(request.getPlotId(), PlotStatusEnum.IRRIGATING);

    }

    @Recover
    private void alertSystem(Exception ex, SensorRequest request, int maxNumberOfRetries) {
        alertService.sendAlert(request.getPlotId());
    }
}
