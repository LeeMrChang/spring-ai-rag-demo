package com.djdj.sect.receiver;

import cn.hutool.json.JSONArray;
import com.djdj.sect.entity.SectServer;
import com.djdj.sect.service.ISectServerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lichanghao
 * @date 2025/1/17
 * @Desc
 */
@Component
@RabbitListener(queues = "topic.sectServer")
@Slf4j
public class SectServerReceiver {

    @Resource
    private ISectServerService sectServerService;

    @RabbitHandler
    public void process(String jsonStr) {
        log.info("SectServerReceiver消费者收到消息: {}", jsonStr);
        sectServerService.truncateTable();
        JSONArray jsonArray = new JSONArray(jsonStr);
        sectServerService.saveBatch(jsonArray.toList(SectServer.class));
        log.info("SectServerReceiver消费消息: {}", jsonStr);
    }
}
