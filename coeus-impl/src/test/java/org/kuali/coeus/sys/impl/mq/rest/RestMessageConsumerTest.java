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
import org.kuali.coeus.sys.framework.auth.AuthConstants;
import org.kuali.coeus.sys.framework.mq.MessageFactory;
import org.kuali.coeus.sys.framework.mq.rest.HttpMethod;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

public class RestMessageConsumerTest {

    private Mockery context;
    private RestDestinationRegistry registry;
    private ConfigurationService configurationService;
    private RestOperations restOperations;
    private RestMessageConsumer restMessageConsumer;

    @Before
    public void setUp() throws Exception {
        restMessageConsumer = new RestMessageConsumer();

        context = new JUnit4Mockery() {{setThreadingPolicy(new Synchroniser());}};
        restOperations = context.mock(RestOperations.class);
        registry = context.mock(RestDestinationRegistry.class);
        configurationService = context.mock(ConfigurationService.class);

        restMessageConsumer.setConsumerRestOperations(restOperations);
        restMessageConsumer.setRestDestinationRegistry(registry);
        restMessageConsumer.setConfigurationService(configurationService);
    }

    @Test(expected = IllegalStateException.class)
    public void destination_not_configured_null() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(HttpMethod.POST);

        context.checking(new Expectations() {
            {
                oneOf(registry).findUrl("destination");
                will(returnValue(null));
                never(restOperations).exchange(with(aNonNull(String.class)), with(aNonNull(org.springframework.http.HttpMethod.class)), with(aNonNull(HttpEntity.class)), with(aNonNull(Class.class)), with(aNonNull(Map.class)));
            }
        });
        restMessageConsumer.onMessage(MessageFactory.createObjectMessage(request));
    }

    @Test(expected = IllegalStateException.class)
    public void destination_not_configured_blank() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(HttpMethod.POST);

        context.checking(new Expectations() {
            {
                oneOf(registry).findUrl("destination");
                will(returnValue(" "));
                never(restOperations).exchange(with(aNonNull(String.class)), with(aNonNull(org.springframework.http.HttpMethod.class)), with(aNonNull(HttpEntity.class)), with(aNonNull(Class.class)),  with(aNonNull(Map.class)));
            }
        });
        restMessageConsumer.onMessage(MessageFactory.createObjectMessage(request));
    }

    @Test(expected = UnknownHttpStatusCodeException.class)
    public void failed_message_status_code() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(HttpMethod.POST);
        final UnknownHttpStatusCodeException response = new UnknownHttpStatusCodeException(500, "Server Error", new HttpHeaders(), new byte[0], Charset.defaultCharset());
        context.checking(new Expectations() {
            {
                oneOf(registry).findUrl("destination");
                will(returnValue("http://www.google.com"));
                oneOf(configurationService).getPropertyValueAsString(AuthConstants.AUTH_SYSTEM_TOKEN_PARAM);
                will(returnValue("top_secret_key"));
                one(restOperations).exchange("http://www.google.com", org.springframework.http.HttpMethod.POST, HttpEntity.EMPTY, Void.class, Collections.emptyMap());
                will(throwException(response));
            }
        });
        restMessageConsumer.onMessage(MessageFactory.createObjectMessage(request));
    }

    @Test
    public void message_sent() {
        RestRequest request = new RestRequest();
        request.setDestination("destination");
        request.setMethod(HttpMethod.POST);
        final ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.ACCEPTED);
        context.checking(new Expectations() {
            {
                oneOf(registry).findUrl("destination");
                will(returnValue("http://www.google.com"));
                oneOf(configurationService).getPropertyValueAsString(AuthConstants.AUTH_SYSTEM_TOKEN_PARAM);
                will(returnValue("top_secret_key"));
                one(restOperations).exchange("http://www.google.com", org.springframework.http.HttpMethod.POST, HttpEntity.EMPTY, Void.class, Collections.emptyMap());
                will(returnValue(response));
            }
        });
        restMessageConsumer.onMessage(MessageFactory.createObjectMessage(request));
    }
}
