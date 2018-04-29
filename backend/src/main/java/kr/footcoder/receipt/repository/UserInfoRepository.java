package kr.footcoder.receipt.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Repository
@AllArgsConstructor
public class UserInfoRepository {

    private RedisTemplate userInfoRedisTemplate;
    private final String PREFIX = "userInfo:";

    private String getKey(int userId){
        return PREFIX + userId;
    }

    @SuppressWarnings("unchecked")
    public void initUserInfo(int userId, String token){
        // 현재시간 + 1일
        long unixTime = Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond();

        userInfoRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {

            RedisSerializer serializer = userInfoRedisTemplate.getStringSerializer();
            byte[] key = serializer.serialize(this.getKey(userId));
            byte[] value = serializer.serialize(token);
            checkNotNull(key, "key must not be null" + userId);
            checkNotNull(value, "value must not be null" + token);

            connection.set(key, value);
            connection.expireAt(key, unixTime);
            return null;
        });
    }





}
