package daggerok.messaging.redis.config.redis;

import daggerok.messaging.redis.config.messaging.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.embedded.RedisServer;

@Configuration
public class RedisCfg {
    @Bean
    public RedisServer redisServer() {
        return RedisServer.builder().port(6379).build();
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(MessageListener messageListener
            , RedisConnectionFactory redisConnectionFactory, String channel) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListener, new PatternTopic(channel));
        return container;
    }

    @Bean
    public MessageListener messageListener(Receiver receiver) {
        return new MessageListenerAdapter(receiver, receiver.defaultListenerMethod());
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
}