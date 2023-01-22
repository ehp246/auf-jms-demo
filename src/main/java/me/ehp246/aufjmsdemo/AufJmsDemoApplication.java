package me.ehp246.aufjmsdemo;

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
import me.ehp246.aufjms.api.annotation.EnableByJms;
import me.ehp246.aufjms.api.annotation.EnableForJms;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound.From;

/**
 * @author Lei Yang
 *
 */
@SpringBootApplication
@EnableByJms
@EnableForJms(@Inbound(@From("${aufjmsdemo.inbox}")))
public class AufJmsDemoApplication {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new MrBeanModule())
            .registerModule(new ParameterNamesModule());

    public static void main(final String[] args) {
        SpringApplication.run(AufJmsDemoApplication.class, args);
    }

    @Bean
    public ConnectionFactory jmsConnectionFactoryQpid(@Value("${aufjmsdemo.broker.url}") final String url,
            @Value("${aufjmsdemo.broker.username}") final String username,
            @Value("${aufjmsdemo.broker.password}") final String password) {
        return new SingleConnectionFactory(new JmsConnectionFactory(username, password, url));
    }

    @Bean
    ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }
}
