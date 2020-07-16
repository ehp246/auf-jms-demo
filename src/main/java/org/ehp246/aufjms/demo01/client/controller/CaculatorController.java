package org.ehp246.aufjms.demo01.client.controller;

import java.util.ArrayList;

import org.ehp246.aufjms.demo01.client.bymsg.Sum;
import org.ehp246.aufjms.demo01.client.bymsg.SumJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lei Yang
 *
 */
@RestController
@RequestMapping("/caculator")
public class CaculatorController {
	private final static Logger LOGGER = LoggerFactory.getLogger(CaculatorController.class);

	@Autowired
	private SumJob submitSumJob;

	@Autowired
	private Sum sum;

	@PostMapping(path = "/job/sum")
	public void postSumJob(@RequestBody final ArrayList<Integer> integers) {
		LOGGER.debug("Received {}", integers.toString());

		submitSumJob.submit(integers);
	}

	@PostMapping(path = "/sum", produces = MediaType.APPLICATION_JSON_VALUE)
	public int postSum(@RequestBody final ArrayList<Integer> integers) {
		LOGGER.debug("Received {}", integers.toString());

		return sum.sum(integers);
	}
}
