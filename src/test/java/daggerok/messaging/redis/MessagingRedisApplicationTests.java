package daggerok.messaging.redis;

import daggerok.messaging.redis.config.messaging.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MessagingRedisApplication.class)
public class MessagingRedisApplicationTests {
	@Autowired ApplicationContext applicationContext;

	@Autowired Sender sender;

	@Autowired CountDownLatch countDownLatch;

	@Test public void contextLoads() {
		assertNotNull("null applicationContext", applicationContext);

		assertTrue("receiver bean wasn't found", applicationContext.containsBean("receiver"));
		assertTrue("sender bean wasn't found", applicationContext.containsBean("sender"));

		assertTrue("redisCfg bean wasn't found", applicationContext.containsBean("redisCfg"));

		assertTrue("cfg bean wasn't found", applicationContext.containsBean("cfg"));

		assertTrue("messagingRedisApplication bean wasn't found", applicationContext.containsBean("messagingRedisApplication"));
	}

	@Test public void testSenderReceiver() throws Exception {
		sender.send("hello, folks!");
		countDownLatch.await();
		// or just run test and verify such line from logs output:
		// ... --- [    Test worker] d.m.redis.config.messaging.Sender        : sending a message...
		// ... --- [enerContainer-2] d.m.redis.config.messaging.Receiver      : received new message! content: hello, folks!
	}
}