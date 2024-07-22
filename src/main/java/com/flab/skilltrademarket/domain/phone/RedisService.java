package com.flab.skilltrademarket.domain.phone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.flab.skilltrademarket.global.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.flab.skilltrademarket.global.exception.ExceptionCode.COMMON_SYSTEM_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService{

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public <T> Optional<T> get(String key, Class<T> type) {
        log.info("Find Redis Key = [{}], Type = [{}]", key, type.getName());
        String serializedValue = redisTemplate.opsForValue().get(key);

        try {
            return Optional.of(objectMapper.readValue(serializedValue, type));
        } catch (IllegalArgumentException | InvalidFormatException e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error("Redis Get Exception", e);
            throw new ApiException(COMMON_SYSTEM_ERROR);
        }
    }


    public void set(String key, Object value, Long expiredTime) {
        log.info("Save Redis Key = [{}], Value = [{}], expiredTime = [{}]", key, value, expiredTime);
        try {
            String serializedValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, serializedValue, expiredTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Redis Set Exception", e);
            throw new ApiException(COMMON_SYSTEM_ERROR);
        }
    }

    public boolean delete(String key) {
        log.info("Delete Redis Key = [{}]", key);
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

}