package org.ehp246.aufjms.demo01.server.formsg;

import java.util.List;

import org.ehp246.aufjms.api.annotation.ForMsg;
import org.ehp246.aufjms.api.annotation.Invoking;

/**
 * @author Lei Yang
 *
 */
@ForMsg
public class Caculator {
	@Invoking
	public int sum(final List<Integer> integers) {
		return integers.stream().reduce(0, Integer::sum);
	}
}
