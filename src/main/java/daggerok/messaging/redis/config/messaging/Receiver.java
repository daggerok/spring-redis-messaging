package daggerok.messaging.redis.config.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

@Component
public class Receiver {
    Logger logger = Logger.getLogger(Receiver.class.getName());

    @Autowired CountDownLatch countDownLatch;

    public void receive(String message) {
        logger.info("received new message! " + message);
        countDownLatch.countDown();
    }

    public String defaultListenerMethod() {
        return "receive";
    }
}