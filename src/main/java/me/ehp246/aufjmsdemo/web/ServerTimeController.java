package in.ehp246.aufjms.demo01.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ehp246.aufjms.demo01.client.bymsg.ServerTime;

/**
 * @author Lei Yang
 *
 */
@RestController
@RequestMapping("/servertime")
public class ServerTimeController {
	@Autowired
	private ServerTime serverTime;

	@GetMapping("/now")
	public String getNow() {
		return serverTime.getNow().toString();
	}
}
