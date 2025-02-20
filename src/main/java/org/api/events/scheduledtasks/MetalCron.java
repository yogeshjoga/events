package org.api.events.scheduledtasks;


import org.api.events.service.goldapi.IGoldSilverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetalCron {

    @Autowired
    private IGoldSilverService goldSilverService;


    @Scheduled(cron = "0 0 0 * * ?")
    public void runAtMidnight() {
        System.out.println("Running at midnight...");
        goldSilverService.getGoldRateAPI();
        goldSilverService.getSilverRateAPI();

    }

    @Scheduled(cron = "0 0 13 * * ?")
    public void runAtOnePM() {
        System.out.println("Running at 1:00 PM...");
        goldSilverService.getGoldRateAPI();
        goldSilverService.getSilverRateAPI();
    }
}