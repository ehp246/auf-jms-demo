package org.ehp246.aufjms.demo01.client.bymsg;

import java.time.Instant;
import java.util.List;

import org.ehp246.aufjms.api.annotation.ByMsg;
import org.ehp246.aufjms.api.annotation.CollectionOf;

/**
 * @author Lei Yang
 *
 */
@ByMsg("queue://demo01.server.caculator.request.queue")
public interface Collector {
	void collect(Instant... instants);

	@CollectionOf(Instant.class)
	List<Instant> getCollected();

	void clear();
}
