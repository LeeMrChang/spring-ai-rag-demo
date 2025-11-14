package com.daily.cost;

import cn.hutool.core.date.DateUtil;
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
    private ISectService sectService;

    @SneakyThrows
    @Test
    void testBuildEntity() {
        sectService.saveOrUpdate(new Sect()
                .setId(100000069L)
                .setName("深圳派")
                .setProfile("/upload_qp/img/20241016/1e5f83b8b341e5639f9d868409271ed8.jpg")
                .setGrade("15-3")
                .setDeclaration("团队齐心~无所不能~携手同行~共创辉煌")
                .setCityId(99L)
                .setCityType(2)
                .setMerchantId(88L)
                .setStatus(true)
                .setContributionValue(888)
                .setGloryScore(BigDecimal.TEN)
                .setWelfareScore(BigDecimal.TWO)
                .setTestFlag(false)
                .setCreateId(0L)
                .setCreateTime(DateUtil.tomorrow())
                .setUpdateId(0L)
                .setUpdateTime(DateUtil.tomorrow())
        );
    }
}
