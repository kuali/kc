package org.kuali.coeus.sys.impl.mq.rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.auth.AuthConstants;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import javax.jms.*;

import java.lang.IllegalStateException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * This rest message consumer subscribes to jms rest messages.  When a message is received the message payload is used
 * construct a REST request.  The message is considered delivered if the REST request returns a non-error return code.
 */
@Component("restMessageConsumer")
public class RestMessageConsumer implements MessageListener {

    private static Log LOG = LogFactory.getLog(RestMessageConsumer.class);

    @Autowired
    @Qualifier("restDestinationRegistry")
    private RestDestinationRegistry restDestinationRegistry;

    @Autowired
    @Qualifier("restOperations")
    private RestOperations consumerRestOperations;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService configurationService;

    @Override
    public void onMessage(Message message) {
        final RestRequest request;
        try {
            request = (RestRequest) ((ObjectMessage) message).getObject();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Consuming Message " + request);
        }

        final String url = retrieveUrl(request);
        if (StringUtils.isBlank(url)) {
            throw new IllegalStateException("url not found for destination " + request.getDestination());
        }
        final Map<String, List<String>> params = request.getParams() != null ? request.getParams() : Collections.emptyMap();
        final HttpHeaders headers = new HttpHeaders();
        if (request.getHeaders() != null) {
            headers.putAll(request.getHeaders());
        }

        headers.put("Authorization", Collections.singletonList("Bearer " + getConfigurationService().getPropertyValueAsString(AuthConstants.AUTH_SYSTEM_TOKEN_PARAM)));

        final HttpEntity<String> entity = StringUtils.isNotBlank(request.getBody()) ? new HttpEntity<>(request.getBody(), headers): HttpEntity.EMPTY;
        final HttpMethod method = HttpMethod.valueOf(request.getMethod().name());

        makeCall(url, params, entity, method);
    }

    public String retrieveUrl(RestRequest request) {
        return restDestinationRegistry.findUrl(request.getDestination());
    }

    protected void makeCall(String url, Map<String, List<String>> params, HttpEntity<String> entity, HttpMethod method) {
        try {
            ResponseEntity<Void> response = consumerRestOperations.exchange(url, method, entity, Void.class, params);
            if (LOG.isDebugEnabled()) {
                LOG.debug(createSentMsg(url, params, entity, method) + " status code "  + response.getStatusCode());
            }
        } catch (UnknownHttpStatusCodeException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(createSentMsg(url, params, entity, method) + " status code "  + e.getRawStatusCode());
            }
            throw e;
        } catch (RuntimeException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(createSentMsg(url, params, entity, method) + " in error " + e.getMessage());
            }
            throw e;
        }
    }

    protected String createSentMsg(String url, Map<String, List<String>> params, HttpEntity<String> entity, HttpMethod method) {
        return "REST request sent for url: " + url + " method " + method + " body " + entity + " parameters " + params;
    }

    public RestDestinationRegistry getRestDestinationRegistry() {
        return restDestinationRegistry;
    }

    public void setRestDestinationRegistry(RestDestinationRegistry restDestinationRegistry) {
        this.restDestinationRegistry = restDestinationRegistry;
    }

    public RestOperations getConsumerRestOperations() {
        return consumerRestOperations;
    }

    public void setConsumerRestOperations(RestOperations consumerRestOperations) {
        this.consumerRestOperations = consumerRestOperations;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
