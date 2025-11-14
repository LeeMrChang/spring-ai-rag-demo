package com.daily.cost;

import cn.hutool.core.date.DateUtil;
import com.daily.cost.entity.User;
import com.daily.cost.service.IUserService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author lichanghao
 * @date 2025/2/11
 * @Desc
 */
@SpringBootTest(args = "--spring.config.location=optional:classpath:application-local.yml,optional:classpath:application.yml")
@Slf4j
public class ISectServiceTest {

    @Resource
    private IUserService userService;

    @SneakyThrows
    @Test
    void testBuildEntity() {
        userService.saveOrUpdate(new User()
                .setNickname("小美")
                        .setRealName("李红敏")
                        .setIdCardNumber("422802198406272181")
                        .setProfile("https://djpub.imos.dongjiao.cc/upload_qp/img/20251107/434a83b52e191bed25e57d88fcd053cd.jpg")
                        .setGender((short) 2)
                        .setPhoneNumber("13695138652")
                        .setTotalAssets(BigDecimal.valueOf(10000))
                .setCreateId(0L)
                .setCreateTime(DateUtil.tomorrow())
                .setUpdateId(0L)
                .setUpdateTime(DateUtil.tomorrow())
        );
    }
}
