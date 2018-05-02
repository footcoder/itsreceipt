package kr.footcoder.receipt.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Repository
@AllArgsConstructor
public class UserInfoRepository {

	private RedisTemplate<String, String> userInfoRedisTemplate;
	private final String PREFIX = "userInfo:";

	private String getKey(String userId){
		return PREFIX + userId;
	}

	/**
	 * 로그인 시 redis 세션 저장
	 * @param token = key : token
	 * @param userId = value : email
	 */
	@SuppressWarnings("unchecked")
	public void initUserInfo(String token, String userId){
		// 현재시간 + 1일
		long unixTime = Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond();

		userInfoRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {

			RedisSerializer serializer = userInfoRedisTemplate.getStringSerializer();
			byte[] key = serializer.serialize(this.getKey(token));
			byte[] value = serializer.serialize(userId);
			checkNotNull(key, "key must not be null" + token);
			checkNotNull(value, "value must not be null" + userId);

			connection.set(key, value);
			connection.expireAt(key, unixTime);
			return null;
		});
	}

	/**
	 * token 값으로 유효한회원의 이메일 조회
	 */
	public String getEmailByUser(String token) {
		return userInfoRedisTemplate.opsForValue().get(this.getKey(token));
	}

	/**
	 * 세션 만료시간 갱신
	 */
	public void refreshSessionOfUser(String token){
		userInfoRedisTemplate.expire(this.getKey(token), 1, TimeUnit.DAYS);
	}



}
