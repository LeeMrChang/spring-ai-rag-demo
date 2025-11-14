package com.djdj.sect.receiver;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.djdj.sect.entity.Sect;
import com.djdj.sect.service.ISectService;
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
@RabbitListener(queues = "topic.sect")
@Slf4j
public class SectReceiver {

    @Resource
    private ISectService sectService;

    @RabbitHandler
    public void process(String jsonStr) {
        log.info("SectReceiver消费者收到消息: {}", jsonStr);
        sectService.truncateTable();
        JSONArray jsonArray = new JSONArray(jsonStr);
        List<Sect> list = jsonArray.toList(Sect.class);
        list.forEach(sect -> {
            sect.setCreateId(0L);
            sect.setCreateTime(DateUtil.date());
            sect.setUpdateId(0L);
            sect.setUpdateTime(DateUtil.date());
            sect.setDelFlag(false);
        });
        sectService.saveBatch(list);
        log.info("SectReceiver消费消息  : {}", JSONUtil.toJsonStr(jsonStr));
    }
}
