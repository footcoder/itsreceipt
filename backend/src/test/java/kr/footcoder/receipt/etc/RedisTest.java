package kr.footcoder.receipt.etc;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedisTest {

    @Autowired
    private RedisTemplate userRedisTemplate;

    @Test
    public void 레디스_접속테스트(){
        System.out.println(userRedisTemplate.opsForValue().get("Hello"));
    }

}
