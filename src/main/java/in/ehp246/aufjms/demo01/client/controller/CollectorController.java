package in.ehp246.aufjms.demo01.client.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ehp246.aufjms.demo01.client.bymsg.Collector;

/**
 * @author Lei Yang
 *
 */
@RestController
@RequestMapping("/collector")
public class CollectorController {
	@Autowired
	private Collector collector;

	@PostMapping("/collect")
	public int collect(@RequestBody final int count) {
		for (int i = 0; i < count; i++) {
			collector.collect(Instant.now());
		}

		return collector.getCollected().size();
	}

	@GetMapping("/size")
	public int getSize() {
		return collector.getCollected().size();
	}

	@PostMapping("/clear")
	public void clear() {
		collector.clear();
	}
}
