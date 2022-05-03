package me.ehp246.aufjmsdemo.inbox.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.ehp246.aufjms.api.annotation.ForJmsType;
import me.ehp246.aufjms.api.annotation.Invoking;

/**
 * @author Lei Yang
 *
 */
@ForJmsType("Sum")
public class Sum {
    private static final Logger logger = LogManager.getLogger();

    @Invoking
    public void sum(final int a, final int b) {
        logger.info("Sum: " + (a + b));
    }
}
