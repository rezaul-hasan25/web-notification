package com.fsmms.web_notification.redis;

public interface iRedisService {

    void saveValue(String nid, String reference, Object value);
    Object getValue(String nid, String reference);
    void removeValue(String key);

}
