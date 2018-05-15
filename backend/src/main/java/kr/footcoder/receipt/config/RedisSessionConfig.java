package kr.footcoder.receipt.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ConfigurationProperties(prefix = "spring")
@Configuration
public class RedisSessionConfig {
	@Setter
	private Map<String, String> redis = new HashMap<>();
	private final int USER_INFO = 0;

	@Bean
	public JedisConnectionFactory connectionFactory(){

		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(new RedisStandaloneConfiguration(redis.get("host")));
		connectionFactory.setDatabase(USER_INFO);

		return connectionFactory;
	}

	@Bean
	public StringRedisTemplate userInfoRedisTemplate(){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(connectionFactory());
		return stringRedisTemplate;

	}
}
