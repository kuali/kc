package org.kuali.coeus.sys.impl.mq.rest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.mq.MessageFactory;
import org.kuali.coeus.sys.framework.mq.rest.HttpMethod;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.springframework.jms.core.JmsOperations;

import javax.jms.ObjectMessage;

public class RestMessageProducerTest {

    private Mockery context;
    private RestDestinationRegistry registry;
    private JmsOperations jmsOperations;
    private RestMessageProducer restMessageProducer;

    @Before
    public void setUp() throws Exception {
        restMessageProducer = new RestMessageProducer();

        context = new JUnit4Mockery() {{setThreadingPolicy(new Synchroniser());}};
        jmsOperations = context.mock(JmsOperations.class);
        registry = context.mock(RestDestinationRegistry.class);

        restMessageProducer.setRestJmsOperations(jmsOperations);
        restMessageProducer.setRestDestinationRegistry(registry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_null_message() {
        restMessageProducer.sendMessage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_message_null_destination() {
        RestRequest request = new RestRequest();
        request.setMethod(HttpMethod.POST);
        request.setDestination(null);
        restMessageProducer.sendMessage(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_message_blank_destination() {
        RestRequest request = new RestRequest();
        request.setMethod(HttpMethod.POST);
        request.setDestination(" ");
        restMessageProducer.sendMessage(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_message_no_method() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(null);
        restMessageProducer.sendMessage(request);
    }

    @Test
    public void test_send_message_destination_enabled() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(HttpMethod.POST);

        context.checking(new Expectations() {
            {
                oneOf(registry).isEnabled("destination");
                will(returnValue(true));
                oneOf(jmsOperations).convertAndSend(with(aNonNull(ObjectMessage.class)));
            }
        });
        restMessageProducer.sendMessage(request);
    }

    @Test
    public void test_send_message_destination_disabled() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(HttpMethod.POST);

        context.checking(new Expectations() {
            {
                oneOf(registry).isEnabled("destination");
                will(returnValue(false));
                never(jmsOperations).convertAndSend(MessageFactory.createObjectMessage(request));
            }
        });
        restMessageProducer.sendMessage(request);
    }
}
