/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
