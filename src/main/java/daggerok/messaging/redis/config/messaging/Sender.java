package daggerok.messaging.redis.config.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Sender {
    Logger logger = Logger.getLogger(Sender.class.getName());

    @Autowired StringRedisTemplate stringRedisTemplate;

    @Autowired String channel;

    public void send(String message) {
        logger.info("sending a message...");
        stringRedisTemplate.convertAndSend(channel, String.format("content: %s", message));
    }
}