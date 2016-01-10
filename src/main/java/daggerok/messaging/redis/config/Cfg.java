package daggerok.messaging.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

@Configuration
@ComponentScan(basePackages = "daggerok")
public class Cfg {
    @Bean public CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }

    @Bean public String channel() {
        return "message-channel";
    }
}