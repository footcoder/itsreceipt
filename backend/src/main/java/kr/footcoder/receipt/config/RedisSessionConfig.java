package kr.footcoder.receipt.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ConfigurationProperties(prefix = "spring")
@EnableRedisHttpSession
public class RedisSessionConfig {

    @Setter
    private Map<String, List<String>> redis = new HashMap<>();

    private final Integer USER_INFO = 0;

/*
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redis.get("host").get(0), Integer.parseInt(redis.get("port").get(0)));
        redisStandaloneConfiguration.setDatabase(USER_INFO);
        return redisStandaloneConfiguration;
    }*/

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redis.get("host").get(0), Integer.parseInt(redis.get("port").get(0)));
        redisStandaloneConfiguration.setDatabase(USER_INFO);
        return new JedisConnectionFactory(redisStandaloneConfiguration);

    }



    @Bean
    public RedisTemplate<String, Object> userRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));

        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        return redisTemplate;
    }


}
