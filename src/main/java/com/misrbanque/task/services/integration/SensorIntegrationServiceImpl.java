package com.misrbanque.task.services.integration;

import com.misrbanque.task.configuration.AppConfiguration;
import com.misrbanque.task.enums.PlotStatusEnum;
import com.misrbanque.task.models.integration.SensorRequest;
import com.misrbanque.task.services.contracts.PlotUpdateService;
import com.misrbanque.task.services.contracts.RetryService;
import com.misrbanque.task.services.contracts.SensorIntegrationService;
import com.misrbanque.task.services.contracts.SensorInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SensorIntegrationServiceImpl implements SensorIntegrationService {

    private final PlotUpdateService plotUpdateService;
    private final AppConfiguration appConfiguration;
    private final SensorInterfaceService interfaceService;
    private final RetryService retryService;

    @Override
    public void handleSensorReading(SensorRequest request) throws Exception {

        boolean isDataSent = interfaceService.sendSensorReading(request);
        if (isDataSent) {
            plotUpdateService.updatePlotStatus(request.getPlotId(), PlotStatusEnum.IRRIGATING);
        } else {
            log.info("Retry Mechanism works here");
            retryService.retrySend(request, appConfiguration.getMaxRetriesCount());
        }

    }

}
