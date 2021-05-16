package com.antra.evaluation.reporting_system;

import com.antra.evaluation.reporting_system.pojo.report.ExcelFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {
    @Autowired
    RedisTemplate<String, ExcelFile> redisTemplate;

    @Test
    public void testSave() {
        String id = "testonly";
        ExcelFile testFile = new ExcelFile();
        testFile.setFileId("testonly");
        System.out.println(testFile);
        System.out.println(redisTemplate);
        redisTemplate.opsForValue().set(id, testFile);
    }

    @Test
    public void testDelete() {
        System.out.println(redisTemplate.delete("testonly"));

    }



}
