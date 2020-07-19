package org.ehp246.aufjms.demo01.server.controller;

import java.time.Instant;

import org.ehp246.aufjms.api.annotation.ForMsg;
import org.ehp246.aufjms.api.annotation.Invoking;
import org.ehp246.aufjms.api.endpoint.InstanceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lei Yang
 *
 */
@ForMsg(value = "ServerTime", scope = InstanceScope.BEAN)
@RestController
@RequestMapping("/time")
public class TimeController {
	private final Logger LOGGER = LoggerFactory.getLogger(TimeController.class);

	@Invoking
	@GetMapping("/now")
	public String getNow() {
		LOGGER.info("Returning server now");
		return Instant.now().toString();
	}
}
