package daggerok.messaging.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SpringBootApplication
public class MessagingRedisApplication {
    Logger logger = Logger.getLogger(MessagingRedisApplication.class.getName());

    @Autowired RedisServer redisServer;

    @PostConstruct
    private void start() throws IOException {
        logger.info("starting redis...");

        redisServer.start();

        logger.info(String.format("redis listen ports: %s", redisServer.ports().stream()
                .map(port -> port.toString()).collect(Collectors.joining(","))));
    }

    @PreDestroy
    public void stop() {
        redisServer.stop();
    }

    public static void main(String[] args) {
        SpringApplication.run(MessagingRedisApplication.class, args)
                .registerShutdownHook();
    }
}