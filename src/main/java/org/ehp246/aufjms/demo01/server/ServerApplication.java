package org.ehp246.aufjms.demo01.server;

import org.ehp246.aufjms.api.annotation.EnableForMsg;
import org.ehp246.aufjms.api.annotation.EnableForMsg.At;
import org.ehp246.aufjms.demo01.server.controller.TimeController;
import org.ehp246.aufjms.demo01.server.formsg.Caculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lei Yang
 *
 */
@SpringBootApplication
@EnableForMsg(@At(value = "${caculator.request}", scan = { Caculator.class, TimeController.class }))
public class ServerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
