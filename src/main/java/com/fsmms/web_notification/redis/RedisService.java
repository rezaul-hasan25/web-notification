package com.fsmms.web_notification.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService implements iRedisService{
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveValue(String nid, String reference, Object value) {
        redisTemplate.opsForHash().put(nid, reference, value);
    }

    @Override
    public Object getValue(String nid, String reference) {
        return (reference != null) ? redisTemplate.opsForHash().get(nid, reference):
                redisTemplate.opsForHash().entries(nid);
    }

    @Override
    public void removeValue(String key) {
        redisTemplate.delete(key);
    }
}
