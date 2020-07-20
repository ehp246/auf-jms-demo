package org.ehp246.aufjms.demo01.server.formsg;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ehp246.aufjms.api.annotation.ForMsg;
import org.ehp246.aufjms.api.annotation.Invoking;
import org.ehp246.aufjms.api.endpoint.InstanceScope;
import org.ehp246.aufjms.api.endpoint.InvocationModel;
import org.springframework.stereotype.Service;

/**
 * This class by itself is not thread-safe. It can not be executed by multiple
 * threads at the same time. The SYNC model ensures for one given end-point,
 * there will not be concurrent invocations. But if the ForMsg is registered on
 * multiple end-points, this assumption does not hold and the code will fail.
 *
 * @author Lei Yang
 *
 */
@Service
@ForMsg(scope = InstanceScope.BEAN, invocation = InvocationModel.SYNC)
public class Collector {
	private final List<Instant> collected = new ArrayList<>();

	@Invoking
	public void collect(final Instant... instants) {
		this.collected.addAll(Arrays.asList(instants));
	}

	@Invoking
	public List<Instant> getCollected() {
		return this.collected;
	}

	@Invoking
	public void clear() {
		this.collected.clear();
	}
}
