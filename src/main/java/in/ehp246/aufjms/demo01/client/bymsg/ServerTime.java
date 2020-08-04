package in.ehp246.aufjms.demo01.client.bymsg;

import java.time.Instant;

import in.ehp246.aufjms.api.annotation.ByMsg;

/**
 * @author Lei Yang
 *
 */
@ByMsg("${caculator.request}")
public interface ServerTime {
	Instant getNow();
}
