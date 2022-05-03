package in.ehp246.aufjms.demo01.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.ehp246.aufjms.api.annotation.EnableForMsg;
import in.ehp246.aufjms.api.annotation.EnableForMsg.At;
import in.ehp246.aufjms.demo01.server.controller.TimeController;
import in.ehp246.aufjms.demo01.server.formsg.Caculator;

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
