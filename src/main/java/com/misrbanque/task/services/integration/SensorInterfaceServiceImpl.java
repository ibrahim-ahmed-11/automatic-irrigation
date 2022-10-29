package com.misrbanque.task.services.integration;

import com.misrbanque.task.models.integration.SensorRequest;
import com.misrbanque.task.services.contracts.SensorInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SensorInterfaceServiceImpl implements SensorInterfaceService {


    @Override
    public boolean sendSensorReading(SensorRequest request) {

        try {
            log.info("Sending signal to sensor of plot: " + request.getPlotId() + " to irrigate " + request.getWaterAmount() + " Liters of water!");
            log.info("******************************************************************");
            log.info("******************************************************************");
            log.info("************ Business of sensor communication is here ************");
            log.info("******************************************************************");
            log.info("******************************************************************");
            log.info("Signal was sent successfully!");
            return true;
        } catch (Exception ex) {
            log.error("Failed to send signal to sensor of plot: " + request.getPlotId() + " to irrigate " + request.getWaterAmount() + " Liters of water!");
            return false;
        }
    }

}
