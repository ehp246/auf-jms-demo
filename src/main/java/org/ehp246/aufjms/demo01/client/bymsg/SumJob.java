package org.ehp246.aufjms.demo01.client.bymsg;

import java.util.List;

import org.ehp246.aufjms.api.annotation.ByMsg;

/**
 * @author Lei Yang
 *
 */
@ByMsg("queue://demo01.server.caculator.request.queue")
public interface SumJob {
	void submit(List<Integer> integers);
}
