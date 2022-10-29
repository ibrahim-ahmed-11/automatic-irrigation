package com.misrbanque.task.services.alert;

import com.misrbanque.task.services.contracts.AlertService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AlertServiceImpl implements AlertService {
    @Override
    public void sendAlert(Long plotId) {
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("************* FAILED TO SEND SIGNAL ********************");
        log.error("************* TO PLOT: " + plotId + " ******************");
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("********************************************************");
        log.error("********************************************************");
    }
}
