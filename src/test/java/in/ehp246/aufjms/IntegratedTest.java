package in.ehp246.aufjms;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

import in.ehp246.aufjms.demo01.client.ApiApplication;
import in.ehp246.aufjms.demo01.client.bymsg.Collector;
import in.ehp246.aufjms.demo01.client.controller.CaculatorController;
import in.ehp246.aufjms.demo01.client.controller.CollectorController;
import in.ehp246.aufjms.demo01.client.controller.ServerTimeController;
import in.ehp246.aufjms.demo01.server.ServerApplication;

/**
 * @author Lei Yang
 *
 */
@SpringBootTest(classes = { ApiApplication.class, ServerApplication.class }, properties = {
		"spring.activemq.broker-url=vm://activemq?broker.persistent=false&broker.useShutdownHook=false" })
public class IntegratedTest {
	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@Test
	public void caculator001() {
		final var controller = beanFactory.getBean(CaculatorController.class);

		Assertions.assertEquals(11, controller.postSum(new ArrayList<>(List.of(1, 10))));
	}

	@Test
	public void collector001() {
		final var controller = beanFactory.getBean(CollectorController.class);

		controller.collect(10);

		Assertions.assertEquals(true, controller.getSize() == 10);

		controller.clear();

		Assertions.assertEquals(true, controller.getSize() == 0);
	}

	@Test
	public void collector002() {
		final var collector = beanFactory.getBean(Collector.class);

		IntStream.range(0, 100).parallel().forEach(i -> collector.collect(Instant.now()));

		Assertions.assertEquals(true, collector.getCollected().size() == 100);

		collector.clear();

		Assertions.assertEquals(true, collector.getCollected().size() == 0);
	}

	@Test
	public void serverTime001() {
		final var controller = beanFactory.getBean(ServerTimeController.class);

		Assertions.assertEquals(true, controller.getNow() != null);
	}
}
