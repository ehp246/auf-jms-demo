package in.ehp246.aufjms.demo01.client.bymsg;

import java.time.Instant;
import java.util.List;

import in.ehp246.aufjms.api.annotation.ByMsg;
import in.ehp246.aufjms.api.annotation.CollectionOf;

/**
 * @author Lei Yang
 *
 */
@ByMsg("${caculator.request}")
public interface Collector {
	void collect(Instant... instants);

	@CollectionOf(Instant.class)
	List<Instant> getCollected();

	void clear();
}
