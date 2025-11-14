package com.djdj.sect.receiver;

import cn.hutool.json.JSONArray;
import com.djdj.sect.entity.Server;
import com.djdj.sect.service.IServerService;
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
@RabbitListener(queues = "topic.server")
@Slf4j
public class ServerReceiver {

    @Resource
    private IServerService serverService;

    @RabbitHandler
    public void process(String jsonStr) {
        log.info("ServerReceiver消费者收到消息: {}", jsonStr);
        serverService.truncateTable();
        JSONArray jsonArray = new JSONArray(jsonStr);
        serverService.saveBatch(jsonArray.toList(Server.class));
        log.info("ServerReceiver消费消息: {}", jsonStr);
    }
}
