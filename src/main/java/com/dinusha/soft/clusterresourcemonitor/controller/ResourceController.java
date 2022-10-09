package com.dinusha.soft.clusterresourcemonitor.controller;

import com.dinusha.soft.clusterresourcemonitor.constant.WebSocketConstant;
import com.dinusha.soft.clusterresourcemonitor.utill.JsonUtil;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/")
@EnableScheduling
@PropertySource("classpath:application.properties")
public class ResourceController {
    private static final Logger logger = Logger.getLogger(ResourceController.class);

    @Value("${delete.unused.tiles}")
    private String deleteTiles;

    @PostMapping("/v1/cpu")
    public void persistData(@RequestBody String cpu) {
        logger.debug("Post call : /v1/cpu : " + cpu);
        JSONObject jsonObject = JsonUtil.JSON_OBJECT.apply(cpu);
        WebSocketConstant.payload.put((String) jsonObject.get("instance"), (String) jsonObject.get("cpu"));
    }

    @Scheduled(cron = "${cron.tile.refresh}")
    public void cleanTile() {
        if (deleteTiles.equals("true")) {
            logger.debug("Deleting unused tiles");
            WebSocketConstant.payload = new HashMap<>();
        } else {
            logger.debug("Deleting unused tiles : off");
        }

    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(TaskSchedulerBuilder builder) {
        return builder.build();
    }
}
