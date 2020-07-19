package org.ehp246.aufjms.demo01.client.bymsg;

import java.time.Instant;

import org.ehp246.aufjms.api.annotation.ByMsg;

/**
 * @author Lei Yang
 *
 */
@ByMsg("queue://demo01.server.caculator.request.queue")
public interface ServerTime {
	Instant getNow();
}
