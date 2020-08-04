package in.ehp246.aufjms.demo01.server.formsg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ehp246.aufjms.api.annotation.ForMsg;
import in.ehp246.aufjms.api.annotation.Invoking;

/**
 * @author Lei Yang
 *
 */
@ForMsg
public class Caculator {
	private final Logger LOGGER = LoggerFactory.getLogger(Caculator.class);

	@Invoking
	public int sum(final List<Integer> integers) {
		return integers.stream().reduce(0, Integer::sum);
	}

	@Invoking
	public void submitSumJob(final List<Integer> integers) {
		LOGGER.info("Job received {}", integers);
	}
}
