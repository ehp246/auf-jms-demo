package org.ehp246.aufjms.demo01.client;

import org.ehp246.aufjms.api.annotation.EnableByMsg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lei Yang
 *
 */
@SpringBootApplication
@EnableByMsg
public class ApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
