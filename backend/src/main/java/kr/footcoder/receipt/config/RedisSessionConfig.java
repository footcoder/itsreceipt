package kr.footcoder.receipt.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ConfigurationProperties(prefix = "spring")
@Configuration
public class RedisSessionConfig {

    @Setter
    private Map<String, List<String>> redis = new HashMap<>();
    private final int USER_INFO = 0;

    @Bean
    public String redisValueTest(){
        log.error("redis host : {}", redis.get("host").get(0));
        log.error("redis host : {}", redis.get("port").get(0));
        return "레디스 호스트포트 잘찍히는지만 확인";
    }

    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(redis.get("host").get(0));
        connectionFactory.setPort(Integer.parseInt(redis.get("port").get(0)));
        connectionFactory.setDatabase(USER_INFO);

        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> userInfoRedisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));

        redisTemplate.setConnectionFactory(connectionFactory());

        return redisTemplate;
    }

}
