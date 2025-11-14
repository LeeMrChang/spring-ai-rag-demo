package com.djdj.sect.receiver;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import com.djdj.sect.entity.SectServer;
import com.djdj.sect.service.ISectServerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<SectServer> list = jsonArray.toList(SectServer.class);
        list.forEach(sectServer -> {
            sectServer.setCreateId(0L);
            sectServer.setCreateTime(DateUtil.date());
            sectServer.setUpdateId(0L);
            sectServer.setUpdateTime(DateUtil.date());
            sectServer.setDelFlag(false);
        });
        sectServerService.saveBatch(list);
        log.info("SectServerReceiver消费消息: {}", jsonStr);
    }
}
