package com.fsmms.web_notification.redis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/redis")
public interface IRedisController {

    @PostMapping("/save/{nid}")
    ResponseEntity<Void> saveValue(
            @PathVariable String nid,
            @RequestParam String reference,
            @RequestBody Object value);

    @GetMapping("/get/{nid}")
    ResponseEntity<Object> getValue(
            @PathVariable String nid,
            @RequestParam(required = false) String reference);
}
