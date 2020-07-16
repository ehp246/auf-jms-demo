package org.ehp246.aufjms.demo01.client.bymsg;

import java.util.List;

import org.ehp246.aufjms.api.annotation.ByMsg;

/**
 * @author Lei Yang
 *
 */
@ByMsg("")
public interface Sum {
	int sum(List<Integer> integers);
}
