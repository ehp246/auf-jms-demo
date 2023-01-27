package me.ehp246.aufjmsdemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.SingleConnectionFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import me.ehp246.aufjms.api.annotation.EnableByJms;
import me.ehp246.aufjms.api.annotation.EnableForJms;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound.From;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound.From.Sub;
import me.ehp246.aufjms.api.jms.DestinationType;

/**
 * @author Lei Yang
 *
 */
@SpringBootApplication
@EnableByJms
@EnableForJms({ @Inbound(@From("${aufjmsdemo.inbox}")),
        @Inbound(value = @From(value = "auf-jms.echo.event", type = DestinationType.TOPIC, sub = @Sub("simple-sub-1")), autoStartup = "true") })
public class AufJmsDemoApplication {
    private final static Logger LOGGER = LogManager.getLogger();

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new MrBeanModule())
            .registerModule(new ParameterNamesModule());

    public static void main(final String[] args) {
        SpringApplication.run(AufJmsDemoApplication.class, args);
    }

    public ConnectionFactory jmsConnectionFactoryQpid(@Value("${aufjmsdemo.broker.url}") final String url,
            @Value("${aufjmsdemo.broker.username}") final String username,
            @Value("${aufjmsdemo.broker.password}") final String password) {
        final var singleConnectionFactory = new SingleConnectionFactory(new JmsConnectionFactory(username, password, url));
        return singleConnectionFactory;
    }

    @Bean
    public JMSContext jmsContext(final ConnectionFactory conFactory) {
        final var jmsContext = conFactory.createContext();
        final var consumer = jmsContext.createConsumer(jmsContext.createTopic("auf-jms.echo.event"));
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(final Message message) {
                try {
                    LOGGER.atInfo().log("Recieved : {}", message.getJMSMessageID());
                } catch (final JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        return jmsContext;
    }
}
