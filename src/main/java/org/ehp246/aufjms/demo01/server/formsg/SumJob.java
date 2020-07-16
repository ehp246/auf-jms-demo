package org.ehp246.aufjms.demo01.server.formsg;

import java.util.List;

import org.ehp246.aufjms.api.annotation.ForMsg;
import org.ehp246.aufjms.api.annotation.Invoking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lei Yang
 *
 */
@ForMsg
public class SumJob {
	private final Logger LOGGER = LoggerFactory.getLogger(SumJob.class);

	@Invoking
	public void submit(final List<Integer> integers) {
		LOGGER.info("Job received {}", integers);
	}
}
